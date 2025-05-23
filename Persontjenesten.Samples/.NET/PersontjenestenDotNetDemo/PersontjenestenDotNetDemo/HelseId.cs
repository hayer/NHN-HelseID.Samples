using IdentityModel;
using IdentityModel.Client;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using System.Security.Cryptography;
using System.Text;
using Microsoft.IdentityModel.JsonWebTokens;

// ReSharper disable once CheckNamespace
namespace PersontjenestenDotNetDemo;

public sealed class HelseIdOptions
{
    public const string SectionKey = "HelseId";

    public string PrivateJwk { get; set; } = string.Empty;
    public string ClientId { get; set; } = string.Empty;
    public string TokenEndpoint { get; set; } = string.Empty;
    public string Scopes { get; set; } = string.Empty;
    public string Authority { get; set; } = string.Empty;
}


public static class HttpClientExtensions
{
    public static IHttpClientBuilder UseHelseIdDPoP(this IHttpClientBuilder httpClientBuilder, Action<HelseIdOptions>? configureClient = null)
    {
        httpClientBuilder.AddHttpMessageHandler(serviceProvider =>
        {
            // Should we validate some options here or leave that to TokenService and DPoPProofCreator?
            var options = serviceProvider.GetRequiredService<IOptions<HelseIdOptions>>();
            ArgumentNullException.ThrowIfNull(options);
            ArgumentNullException.ThrowIfNull(options.Value);

            var settings = options.Value;
            if (configureClient is not null)
            {
                configureClient(settings);
            }

            ArgumentException.ThrowIfNullOrWhiteSpace(settings.PrivateJwk);
            ArgumentException.ThrowIfNullOrWhiteSpace(settings.ClientId);
            ArgumentException.ThrowIfNullOrWhiteSpace(settings.TokenEndpoint);
            ArgumentException.ThrowIfNullOrWhiteSpace(settings.Scopes);
            ArgumentException.ThrowIfNullOrWhiteSpace(settings.Authority);

            var opt = Options.Create(settings);
            var loggerFactory = serviceProvider.GetRequiredService<ILoggerFactory>();
            var httpClientFactory = serviceProvider.GetRequiredService<IHttpClientFactory>();
            var dPoPProofCreator = new DPoPProofCreator(opt, loggerFactory.CreateLogger<DPoPProofCreator>());
            var tokenService = new TokenService(opt, loggerFactory.CreateLogger<TokenService>(), httpClientFactory, dPoPProofCreator);

            return new HelseIdDelegatingHandler(dPoPProofCreator, tokenService);
        });
        return httpClientBuilder;
    }
}


internal class HelseIdDelegatingHandler(DPoPProofCreator dPoPProofCreator, TokenService tokenService)
    : DelegatingHandler
{
    protected override async Task<HttpResponseMessage> SendAsync(
        HttpRequestMessage request,
        CancellationToken cancellationToken)
    {
        var accessToken = await tokenService.GetAccessToken(cancellationToken);
        if (request.RequestUri != null)
        {
            var proof = dPoPProofCreator.CreateDPoPProof(
                request.RequestUri.ToString(),
                request.Method.ToString(),
                null,
                accessToken);
            request.SetDPoPToken(accessToken, proof);
        }

        return await base.SendAsync(request, cancellationToken);
    }
}


public class DPoPProofCreator(IOptions<HelseIdOptions> options, ILogger<DPoPProofCreator> logger)
{
    public string CreateDPoPProof(string url, string httpMethod, string? dPoPNonce = null, string? accessToken = null)
    {
        logger.LogDebug("Creating DPoP proof for url {Url} with nonce: {Nonce} ", url, dPoPNonce);

        var securityKey = new JsonWebKey(options.Value.PrivateJwk);
        var signingCredentials = new SigningCredentials(securityKey, "RS256");

        var jwk = securityKey.Kty switch
        {
            JsonWebAlgorithmsKeyTypes.EllipticCurve => new Dictionary<string, string>
            {
                [JsonWebKeyParameterNames.Kty] = securityKey.Kty,
                [JsonWebKeyParameterNames.X] = securityKey.X,
                [JsonWebKeyParameterNames.Y] = securityKey.Y,
                [JsonWebKeyParameterNames.Crv] = securityKey.Crv,
            },
            JsonWebAlgorithmsKeyTypes.RSA => new Dictionary<string, string>
            {
                [JsonWebKeyParameterNames.Kty] = securityKey.Kty,
                [JsonWebKeyParameterNames.N] = securityKey.N,
                [JsonWebKeyParameterNames.E] = securityKey.E,
                [JsonWebKeyParameterNames.Alg] = signingCredentials.Algorithm,
            },
            _ => throw new InvalidOperationException("Invalid key type for DPoP proof.")
        };

        var urlWithoutQuery = url.Split('?')[0];
        var tokenDescriptor = new SecurityTokenDescriptor
        {
            SigningCredentials = signingCredentials,
            AdditionalHeaderClaims = new Dictionary<string, object>
            {
                { JwtClaimTypes.JsonWebKey, jwk },
            },

            Claims = new Dictionary<string, object>
            {
                { JwtClaimTypes.JwtId,  Guid.NewGuid().ToString() },
                { JwtClaimTypes.DPoPHttpMethod, httpMethod },
                { JwtClaimTypes.DPoPHttpUrl, urlWithoutQuery },
                { JwtClaimTypes.IssuedAt, DateTimeOffset.UtcNow.ToUnixTimeSeconds() },
            },
            TokenType = "dpop+jwt"
        };

        // Used when accessing the authentication server (HelseID):
        if (!string.IsNullOrEmpty(dPoPNonce))
        {
            // nonce: A recent nonce provided via the DPoP-Nonce HTTP header.
            tokenDescriptor.Claims.Add(JwtClaimTypes.Nonce, dPoPNonce);
        }

        // Used when accessing an API that requires a DPoP token:
        if (!string.IsNullOrEmpty(accessToken))
        {
            // ath: hash of the access token. The value MUST be the result of a base64url encoding
            // the SHA-256 [SHS] hash of the ASCII encoding of the associated access token's value.
            var hash = SHA256.HashData(Encoding.ASCII.GetBytes(accessToken));
            var ath = Base64Url.Encode(hash);

            tokenDescriptor.Claims.Add(JwtClaimTypes.DPoPAccessTokenHash, ath);
        }

        return new JsonWebTokenHandler().CreateToken(tokenDescriptor);
    }
}


public class TokenService(
    IOptions<HelseIdOptions> options,
    ILogger<TokenService> logger,
    IHttpClientFactory httpClientFactory,
    DPoPProofCreator dPoPProofCreator)
{
    private DateTime _cachedAccessTokenExpiresAt = DateTime.MinValue;
    private string _cachedAccessToken = string.Empty;
    private HttpClient? _httpClient;

    private HttpClient HttpClient => _httpClient ??= httpClientFactory.CreateClient("HelseId.TokenService");

    public async Task<string> GetAccessToken(CancellationToken cancellationToken)
    {
        if (DateTime.UtcNow <= _cachedAccessTokenExpiresAt)
        {
            logger.LogDebug("Using cached DPoP access token");
            return _cachedAccessToken;
        }

        logger.LogDebug("Getting DPoP access token from HelseId");
        var tokenResponse = await GetAccessTokenFromHelseId(cancellationToken);

        _cachedAccessTokenExpiresAt =
            DateTime.UtcNow.AddSeconds(tokenResponse.ExpiresIn - 30); // Refresh token 30 seconds ahead of expiry
        _cachedAccessToken = tokenResponse.AccessToken!;

        return _cachedAccessToken;
    }

    private async Task<TokenResponse> GetAccessTokenFromHelseId(CancellationToken cancellationToken)
    {
        // 1. Send a token request without a DPoP nonce
        var firstRequest = CreateTokenRequest();
        var firstTokenResponse = await HttpClient.RequestClientCredentialsTokenAsync(firstRequest, cancellationToken);
        if (!firstTokenResponse.IsError || string.IsNullOrEmpty(firstTokenResponse.DPoPNonce))
        {
            throw new Exception("Expected a DPoP nonce to be returned from the authorization server.");
        }

        // 2. Send a second token request with the DPoP nonce from the first response
        var secondRequest = CreateTokenRequest(firstTokenResponse.DPoPNonce);
        var secondTokenResponse = await HttpClient.RequestClientCredentialsTokenAsync(secondRequest, cancellationToken);
        if (secondTokenResponse.IsError || secondTokenResponse.AccessToken == null)
        {
            throw new Exception($"Error retrieving access token: {secondTokenResponse.Error}");
        }

        return secondTokenResponse;
    }

    private ClientCredentialsTokenRequest CreateTokenRequest(string? dPoPNonce = null)
    {
        var securityKey = new JsonWebKey(options.Value.PrivateJwk);
        var signingCredentials = new SigningCredentials(securityKey, SecurityAlgorithms.RsaSha512);

        var claims = new Dictionary<string, object>
        {
            { JwtClaimTypes.Subject, options.Value.ClientId },
            { JwtClaimTypes.IssuedAt, new DateTimeOffset(DateTime.UtcNow).ToUnixTimeSeconds() },
            { JwtClaimTypes.JwtId, Guid.NewGuid().ToString("N") }
        };

        var tokenDescriptor = new SecurityTokenDescriptor
        {
            SigningCredentials = signingCredentials,
            TokenType = "client-authentication+jwt",
            Issuer = options.Value.ClientId,
            Audience = options.Value.Authority,
            Claims = claims,
            Expires = DateTime.Now.AddMinutes(1),
            NotBefore = DateTime.Now,
        };

        var tokenHandler = new JsonWebTokenHandler();
        var clientAssertion = tokenHandler.CreateToken(tokenDescriptor);

        var request = new ClientCredentialsTokenRequest
        {
            Address = options.Value.TokenEndpoint,
            ClientAssertion =
                new ClientAssertion
                {
                    Value = clientAssertion,
                    Type = OidcConstants.ClientAssertionTypes.JwtBearer
                },
            ClientId = options.Value.ClientId,
            Scope = options.Value.Scopes,
            GrantType = OidcConstants.GrantTypes.ClientCredentials,
            ClientCredentialStyle = ClientCredentialStyle.PostBody,
            DPoPProofToken =
                dPoPProofCreator.CreateDPoPProof(options.Value.TokenEndpoint, "POST", dPoPNonce: dPoPNonce)
        };

        return request;
    }
}

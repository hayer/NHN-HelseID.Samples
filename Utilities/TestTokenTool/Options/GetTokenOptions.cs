using CommandDotNet;
using TestTokenTool.Constants;
using TestTokenTool.InputParameters;
using TestTokenTool.RequestModel;

// ReSharper disable InconsistentNaming

namespace TestTokenTool.Options;

public class GetTokenOptions : IArgumentModel
{
    // -----------------------------------------
    // Output from application
    [Named('p', Description = "The returned token is printed in JWT format on screen", BooleanMode = BooleanMode.Explicit)]
    public bool printToken { get; set; } = true;
    [Named(Description = "The returned token is printed in JSON format on screen")]
    public bool prettyPrintToken { get; set; } = false;
    [Named('s', Description = $"The returned token is saved to a file named '{FileConstants.TokenFileName}'")]
    public bool saveTokenToFile { get; set; } = false;
    // -----------------------------------------
    // Invalid output from TTT
    [Named(Description = "The returned token will be signed with an invalid signing key")]
    public bool signJwtWithInvalidSigningKey { get; set; } = false;
    [Named(Description = "The returned token will contain an invalid 'iss' claim")]
    public bool setInvalidIssuer { get; set; } = false;
    [Named(Description = "The returned token will contain an invalid 'aud' claim")]
    public bool setInvalidAudience { get; set; } = false;
    [Named(Description = "The returned token will contain an expired 'nbf', 'iat', and 'exp' claims")]
    public bool setExpirationTimeAsExpired { get; set; } = false;
    [Named(Description = "The returned token will contain an 'exp' claim matching the set expiration time in seconds")]
    public int expirationTimeInSeconds { get; set; } = 600;
    [Named(Description = "The returned token will contain an 'exp' claim matching the set expiration time (days) in seconds")]
    public int expirationTimeInDays { get; set; } = 0;
    // -----------------------------------------
    // Header parameters
    [Named(Description = "The returned token will contain a 'typ' header matching the injected value. Accepted values are 'jwt' and 'at+jwt'")]
    public string? typHeader { get; set; } = string.Empty;
    // -----------------------------------------
    // General parameters
    [Named(Description = "The returned token will contain an audience claim that matches the injected value. If this is not set, the audience will be set as 'nhn:some:api'.")]
    public string? audience { get; set; } = "nhn:some:api";
    [Named(Description = "The returned token will contain an issuer claim that matches the injected value")]
    public IssuerEnvironment issuerEnvironment { get; set; } = IssuerEnvironment.Test;
    [Named(Description = "The returned token will contain a 'client_amr' claim matching the injected value")]
    public string? clientAmr { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'helseid://claims/client/amr' claim matching the injected value")]
    public string? helseidClientAmr { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'helseid://claims/client/claims/orgnr_parent' claim matching the injected value")]
    public string? orgnrParent { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'helseid://claims/client/claims/orgnr_child' claim matching the injected value")]
    public string? orgnrChild { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'helseid://claims/client/claims/orgnr_supplier' claim matching the injected value")]
    public string? orgnrSupplier { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'helseid://claims/client/claims/client_tenancy' claim matching the injected value if 'clientTenancyType' is set")]
    public bool clientTenancy { get; set; }
    [Named(Description = "The returned token will contain a 'helseid://claims/client/claims/client_tenancy' claim with either 'none', 'single-tenant', or 'multi-tenant', matching the injected value if 'clientTenancy' is set")]
    public ClientTenancyType clientTenancyType { get; set; } = ClientTenancyType.MultiTenant;
    [Named(Description = "The returned token will contain a 'nhn:sfm:journal_id' claim matching the injected value")]
    public Guid? sfmJournalId { get; set;} = default;
    [Named(Description = "The returned token will contain a 'client_id' claim matching the injected value")]
    public Guid? clientId { get; set;} = default;
    [Named(Description = "The returned token will contain a 'helseid://claims/client/client_name' claim matching the injected value")]
    public string? clientName { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'scope' claim matching the injected value. Use quotes and spaces to insert several scopes.")]
    public string? scope { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'cnf' claim matching the injected value")]
    public string? cnfJkt { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'cnf' claim with a hash matching the injected value")]
    public string? cnfPublicKey { get; set; } = string.Empty;
    // -----------------------------------------
    // User parameters
    [Named(Description = "The returned token will contain an 'amr' claim matching the injected value")]
    public string? amr { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain an 'idp' claim matching the injected value")]
    public string? identityProvider { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'helseid://claims/identity/assurance_level' claim matching the injected value")]
    public string? assuranceLevel { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'helseid://claims/identity/security_level' claim matching the injected value")]
    public string? securityLevel { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a claim with a pseudonymized pid value. Requires that the 'pid' parameter is set.")]
    public bool setPidPseudonym { get; set; } = false;
    [Named(Description = "The returned token will contain a claim with a pseudonymized pid value in the 'sub' claim. Requires that both the 'pid' and 'clientid' parameters are set.")]
    public bool setSubject { get; set; } = false;
    [Named(Description = "The returned token will contain a 'pid' claim matching the injected value")]
    public string? pid { get; set; } = string.Empty;
    [Named(Description = "If this value is set, the PID value will be used to extract person information from Persontjenesten. Requires that the 'pid' parameter is set.")]
    public bool getPersonFromPersontjenesten { get; set; } = false;
    [Named(Description = "If this value and getPersonFromPersontjenesten are set, only the 'name' claim will be issued, not the claims 'given_name', 'middle_name', or 'family_name'.")]
    public bool onlySetNameForPerson { get; set; } = false;
    [Named(Description = "The returned token will contain a 'helseid://claims/identity/pid_pseudonym' claim matching the injected value. If setPidPseudonym is true, this option will be overridden.")]
    public string? pidPseudonym { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'name' claim matching the injected value. Requires that the 'getPersonFromPersontjenesten' parameter is not set.")]
    public string? name { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'given_name' claim matching the injected value. Requires that the 'getPersonFromPersontjenesten' parameter is not set.")]
    public string? given_name { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'middle_name' claim matching the injected value. Requires that the 'getPersonFromPersontjenesten' parameter is not set.")]
    public string? middle_name { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'family_name' claim matching the injected value. Requires that the 'getPersonFromPersontjenesten' parameter is not set.")]
    public string? family_name { get; set; } = string.Empty;
    [Named(Description = "If this value is set, the PID value will be used to extract person information from HPR-registeret.")]
    public bool getHprNumberFromHprregisteret { get; set; } = false;
    [Named(Description = "The returned token will contain a 'helseid://claims/hpr/hpr_number' claim matching the injected value. Requires that the 'getHprNumberFromHprregisteret' parameter is not set.")]
    public string? hprNumber { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'network' claim matching the injected value")]
    public string? network { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'sid' claim matching the injected value")]
    public string? sid { get; set; } = string.Empty;
    [Named(Description = "The returned token will contain a 'sub' claim matching the injected value")]
    public string? sub { get; set; } = string.Empty;
    // -----------------------------------------
    // Tillitsrammeverk parameters
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public bool dontSetPractitionerHprNr { get; set; } = false;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public bool dontSetPractitionerAuthorization { get; set; } = false;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public bool dontSetPractitionerDepartment { get; set; } = false;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public bool dontSetCareRelationshipHealthcareService { get; set; } = false;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public bool dontSetCareRelationshipPurposeOfUseDetails { get; set; } = false;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public bool dontSetPatientsPointOfCare { get; set; } = false;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public bool dontSetPatientsDepartment { get; set; } = false;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerIdentifierSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerIdentifierAuthority { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerHprNrSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerHprNrAuthority { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerAuthorizationCode { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerAuthorizationText { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerAuthorizationSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerAuthorizationAssigner { get; set; } = string.Empty;

    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerLegalEntityId { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerLegalEntityName { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerLegalEntitySystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerLegalEntityAuthority { get; set; } = string.Empty;
    
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerPointOfCareId { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerPointOfCareName { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerPointOfCareSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerPointOfCareAuthority { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerDepartmentId { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerDepartmentName { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerDepartmentSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string practitionerDepartmentAuthority { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipHealthcareServiceCode { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipHealthcareServiceText { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipHealthcareSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipHealthcareAssigner { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipPurposeOfUseCode { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipPurposeOfUseText { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipPurposeOfUseSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipPurposeOfUseAssigner { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipPurposeOfUseDetailsCode { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipPurposeOfUseDetailsText { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipPurposeOfUseDetailsSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipPurposeOfUseDetailsAssigner { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string careRelationshipDecisionRefId { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public bool careRelationshipDecisionRefUserSelected { get; set; } = true;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string patientsPointOfCareId { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string patientsPointOfCareName { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string patientsPointOfCareSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string patientsPointOfCareAuthority { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string patientsDepartmentId { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string patientsDepartmentName { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string patientsDepartmentSystem { get; set; } = string.Empty;
    [Named(Description = "Parameter for use in 'tillitsrammeverk' claims")]
    public string patientsDepartmentAuthority { get; set; } = string.Empty;
    // -----------------------------------------
    // Usage of parameters
    [Named(Description = "No default client claims values are created")]
    public bool withoutDefaultClientClaims { get; set; } = false;
    [Named(Description = "No default user claims values are created")]
    public bool withoutDefaultUserClaims { get; set; } = false;
    [Named(Description = "Instructs how client claims are created")]
    public ClaimGeneration clientClaimsCreation { get; set; } = ClaimGeneration.DefaultWithParameterValues;
    [Named(Description = "Instructs how user claims are created")]
    public ClaimGeneration userClaimsCreation { get; set; } = ClaimGeneration.DefaultWithParameterValues;
    [Named(Description = "Create claims for tillitsrammeverk")]
    public bool createTillitsrammeverkClaims { get; set; } = false;
    // DPoP parameters
    [Named(Description = "This will create a DPoP proof and a corresponding 'cnf' claim in the returned token")]
    public bool createDPoPTokenWithDPoPProof { get; set; } = false;
    [Named(Description = "This parameter will be set as the 'htu' claim value in the DPoP proof. If 'createDPoPTokenWithDPoPProof' is set, and 'dontSetHtuClaimValue' is not set, this parameter must have a value.")]
    public string htuClaimValue { get; set; } = string.Empty;
    [Named(Description = "This parameter will be set as the 'htm' claim value for the DPoP proof. If 'createDPoPTokenWithDPoPProof' is set, and 'dontSetHtmClaimValue' is not set, this parameter must have a value. Accepted values are methods as described in RFC9110 (https://www.rfc-editor.org/rfc/rfc9110#name-method-definitions)")]
    public string htmClaimValue { get; set; } = string.Empty;
    [Named(Description = "If this value is set, an invalid DPoP proof will we returned")]
    public InvalidDPoPProofParameters invalidDPoPProof { get; set; } = InvalidDPoPProofParameters.None;
    [Named(Description = "When calling an API with a DPoP proof, the API is called twice with the same DPoP proof. This should trigger an error on the second call.")]
    public bool reuseDpopProofForApi { get; set; } = false;
    [Named(DescriptionLines = new[] {"If set with a valid jwk, the DPoP proof will be signed with this key", "", "API options:"})]
    public string privateKeyForProofCreation { get; set; } = string.Empty;
    [Named(Description = "If this value is set, the application will call the sample API with the returned token")]
    public bool callApi { get; set; } = false;
    [Named(Description = "Type for an API specific claim. Both type and value must be set to obtain an API specific claim.")]
    public string apiSpecificClaimType { get; set; } = string.Empty;
    [Named(Description = "Value for an API specific claim. Both type and value must be set to obtain an API specific claim.")]
    public string apiSpecificClaimValue { get; set; } = string.Empty;
}

namespace TestTokenTool.RequestModel;

public class UserClaimsParameters 
{
    // helseid://claims/identity/pid
    public string Pid { get; set; } = string.Empty;
    // helseid://claims/identity/pid_pseudonym
    public string PidPseudonym { get; set; } = string.Empty;
    // helseid://claims/hpr/hpr_number
    public string HprNumber { get; set; } = string.Empty;
    // name
    public string Name { get; set; } = string.Empty;
    // idp
    public string IdentityProvider { get; set; } = string.Empty;
    // helseid://claims/identity/security_level
    public string SecurityLevel { get; set; } = string.Empty;
    // helseid://claims/identity/assurance_level
    public string AssuranceLevel { get; set; } = string.Empty;
    // helseid://claims/identity/network
    public string Network { get; set; } = string.Empty;
    // sub
    public string Subject { get; set; } = string.Empty;
    // sid
    public string Sid { get; set; } = string.Empty;
}
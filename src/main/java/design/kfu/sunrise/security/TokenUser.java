package design.kfu.sunrise.security;

import design.kfu.sunrise.util.JwtClaimUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenUser {
    private String name;
    private List<String> roles;
    private String email;
    private String surname;

    public TokenUser(Map<String, Object> claims) {
        this.name = JwtClaimUtils.getClaimAsString(claims, Claims.Person.NAME);
        this.roles = JwtClaimUtils
                .getClaimAsListString(
                        (Map<String, Object>) claims.getOrDefault("realm_access", ""), Claims.Person.ROLES);
        this.email = JwtClaimUtils.getClaimAsString(claims, Claims.Person.EMAIL);
        this.surname = JwtClaimUtils.getClaimAsString(claims, Claims.Person.FAMILY_NAME);
    }
}

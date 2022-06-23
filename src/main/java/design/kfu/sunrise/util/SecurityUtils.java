package design.kfu.sunrise.util;

import design.kfu.sunrise.security.TokenUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

@UtilityClass
public class SecurityUtils {

    /**
     * Получить текущего пользователя из токена
     */
    public Optional<TokenUser> getUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable((JwtAuthenticationToken) securityContext.getAuthentication())
                .map(JwtAuthenticationToken::getToken)
                .map(Jwt::getClaims)
                .map(TokenUser::new);
    }
}

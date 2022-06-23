package design.kfu.sunrise.security.details;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.util.SecurityUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserDetailsImpl implements UserDetails {
    @Getter
    @Setter
    private Account account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityUtils.getUser().orElseThrow(Exc.sup(ErrorType.ENTITY_NOT_FOUND))
                .getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

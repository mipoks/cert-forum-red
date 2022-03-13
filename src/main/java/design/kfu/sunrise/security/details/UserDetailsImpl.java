package design.kfu.sunrise.security.details;

import design.kfu.sunrise.domain.model.Account;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserDetailsImpl implements UserDetails {
  private Account account;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(account.getRole().toString());
    log.info("AUTHORITY -> {}", authority.getAuthority());
    return Collections.singleton(authority);
  }

  @Override
  public String getPassword() {
    return account.getHashPassword();
  }

  @Override
  public String getUsername() {
    return account.getLogin();
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

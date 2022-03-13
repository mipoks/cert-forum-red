package design.kfu.sunrise.security.details;

import design.kfu.sunrise.domain.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired private Acc userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("Loading user details username  is{}", username);
    Account user =
        userRepository
            .getUserByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new ru.softwave.pool.security.details.UserDetailsImpl(user);
  }
}

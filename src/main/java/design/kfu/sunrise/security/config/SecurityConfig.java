package design.kfu.sunrise.security.config;

import design.kfu.sunrise.security.entrypoint.MyBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  MyBasicAuthenticationEntryPoint authenticationEntryPoint;
  @Autowired private PasswordEncoder passwordEncoder;

  @Qualifier("userDetailsServiceImpl")
  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    try {
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.headers().frameOptions().sameOrigin();
    httpSecurity
        .httpBasic()
        .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            //ToDo переделать
        .authorizeRequests().antMatchers(HttpMethod.GET,"/**")//.authenticated()
//        .antMatchers(  "css/**", "js/**")
        .permitAll()
        .and()
        .formLogin()
        .loginProcessingUrl("/login")
        .successForwardUrl("/success/welcome")
        .failureForwardUrl("/unauthorized")
        .and()
        .csrf()
        .disable();
  }
}

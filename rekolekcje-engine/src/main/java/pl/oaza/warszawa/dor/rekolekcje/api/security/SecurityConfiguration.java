package pl.oaza.warszawa.dor.rekolekcje.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtAuthenticationTokenFilter;

@Configuration
class SecurityConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationTokenFilter authenticationTokenFilter() {
    return new JwtAuthenticationTokenFilter();
  }
}

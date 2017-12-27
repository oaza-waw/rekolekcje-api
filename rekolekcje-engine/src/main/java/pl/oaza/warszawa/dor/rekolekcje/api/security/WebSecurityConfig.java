package pl.oaza.warszawa.dor.rekolekcje.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtAuthenticationEntryPoint;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtAuthenticationTokenFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  private final JwtAuthenticationEntryPoint unauthorizedHandler;
  private final UserDetailsService userDetailsService;
  private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public WebSecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler,
                           UserDetailsService userDetailsService,
                           JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
                           PasswordEncoder passwordEncoder) {
    this.unauthorizedHandler = unauthorizedHandler;
    this.userDetailsService = userDetailsService;
    this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    this.passwordEncoder = passwordEncoder;
  }


  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    logger.info("Configuring jwt authentication...");
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    logger.info("Configuring security...");
    httpSecurity
        .csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .authorizeRequests()
        .antMatchers("/users/sign-up").permitAll()
        .antMatchers("/auth").permitAll()
        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
        .anyRequest().authenticated().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    httpSecurity
        .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    httpSecurity.headers().cacheControl();
  }
}

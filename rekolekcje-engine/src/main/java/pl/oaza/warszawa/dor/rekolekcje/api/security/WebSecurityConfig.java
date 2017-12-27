package pl.oaza.warszawa.dor.rekolekcje.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtAuthenticationEntryPoint;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtAuthenticationTokenFilter;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtTokenUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Autowired
  private UserDetailsService userDetailsService;

//  @Autowired
//  private JwtTokenUtil jwtTokenUtil;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationTokenFilter authenticationTokenFilter() {
    return new JwtAuthenticationTokenFilter();
  }

//  @Autowired
//  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder)
//      throws Exception {
//    logger.info("Configuring jwt authentication...");
//    authenticationManagerBuilder
//        .userDetailsService(userDetailsService);
//        .passwordEncoder(passwordEncoder());
//  }

  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.inMemoryAuthentication()
        .withUser("admin").password("admin").roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    logger.info("Configuring security...");
    httpSecurity
        .csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers("/auth/**").permitAll()
        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
        .anyRequest().authenticated();

    httpSecurity
        .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    httpSecurity.headers().cacheControl();
  }
}

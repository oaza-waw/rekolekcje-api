package pl.oaza.warszawa.dor.rekolekcje.api.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Value("${jwt.header}")
  private String tokenHeader;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String authToken = request.getHeader(this.tokenHeader);

    if (authToken != null && authToken.startsWith("Bearer ")) {
      authToken = authToken.substring(7);
    }

    String username = jwtTokenUtil.getUsernameFromToken(authToken);

    log.info("Checking authentication for user {}", username);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      // It is not compelling necessary to load the use details from the database. You could also store the information
      // in the token and read it from it. It's up to you ;)
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      if(jwtTokenUtil.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        log.info("Authenticated user {}, setting security context", username);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}

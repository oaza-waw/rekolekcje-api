package pl.oaza.warszawa.dor.rekolekcje.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtAuthenticationRequest;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtAuthenticationResponse;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtTokenUtil;
import pl.oaza.warszawa.dor.rekolekcje.api.security.jwt.JwtUser;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationRestEndpoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationRestEndpoint.class);

  @Value("${jwt.header}")
  private String tokenHeader;

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserDetailsService userDetailsService;

  @Autowired
  public AuthenticationRestEndpoint(
      AuthenticationManager authenticationManager,
      JwtTokenUtil jwtTokenUtil,
      UserDetailsService userDetailsService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userDetailsService = userDetailsService;
  }

  @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
  public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(
      @RequestBody JwtAuthenticationRequest authenticationRequest) {
    LOGGER.info("Creating authentication token for user {}", authenticationRequest.getUsername());

    // Perform the security
    final Authentication authentication =
        authenticationManager.authenticate(createTokenFromRequest(authenticationRequest));

    LOGGER.info("User {} is authenticated: {}", authenticationRequest.getUsername(), authentication.isAuthenticated());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-security, so we can generate token
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);

    // Return the token
    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }

  private UsernamePasswordAuthenticationToken createTokenFromRequest(
      JwtAuthenticationRequest authenticationRequest) {
    return new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
    );
  }

  @PostMapping(value = "${jwt.route.authentication.refresh}")
  public ResponseEntity<JwtAuthenticationResponse> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      LOGGER.info("Refreshing token for user: {}", username);
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }
}

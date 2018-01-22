package pl.oaza.warszawa.dor.rekolekcje.api.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtTokenUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

  static final String CLAIM_KEY_USERNAME = "sub";
  static final String CLAIM_KEY_AUDIENCE = "audience";
  static final String CLAIM_KEY_CREATED = "created";

  private static final String AUDIENCE_WEB = "web";

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private long expiration;

  public String getUsernameFromToken(String token) {
    String username;
    try {
      final Claims claims = getClaimsFromToken(token).orElseThrow(ClaimsNotFoundException::new);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
    }
    return username;
  }

  public Date getCreatedDateFromToken(String token) {
    Date created;
    try {
      final Claims claims = getClaimsFromToken(token).orElseThrow(ClaimsNotFoundException::new);
      created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
    } catch (Exception e) {
      created = null;
    }
    return created;
  }

  public Date getExpirationDateFromToken(String token) {
    Date expirationDate;
    try {
      final Claims claims = getClaimsFromToken(token).orElseThrow(ClaimsNotFoundException::new);
      expirationDate = claims.getExpiration();
    } catch (Exception e) {
      expirationDate = null;
    }
    return expirationDate;
  }

  private Optional<Claims> getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      return Optional.empty();
    }
    return Optional.of(claims);
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + expiration * 1000);
  }

  private Boolean isTokenExpired(String token) {
    final Date expirationDate = getExpirationDateFromToken(token);
    return expirationDate.before(new Date());
  }

  private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
    return (lastPasswordReset != null && created.before(lastPasswordReset));
  }

  private String generateAudience() {
    return AUDIENCE_WEB;
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
    claims.put(CLAIM_KEY_AUDIENCE, generateAudience());
    claims.put(CLAIM_KEY_CREATED, new Date());
    return generateToken(claims);
  }

  String generateToken(Map<String, Object> claims) {
    LOGGER.info("JWT: Generating token...");
    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(generateExpirationDate())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
    final Date created = getCreatedDateFromToken(token);
    return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && !isTokenExpired(token);
  }

  public String refreshToken(String token) {
    LOGGER.info("Refreshing token... ");
    String refreshedToken;
    try {
      final Claims claims = getClaimsFromToken(token).orElseThrow(ClaimsNotFoundException::new);
      claims.put(CLAIM_KEY_CREATED, new Date());
      refreshedToken = generateToken(claims);
    } catch (Exception e) {
      refreshedToken = null;
    }
    return refreshedToken;
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    JwtUser user = (JwtUser) userDetails;
    final String username = getUsernameFromToken(token);
    final Date created = getCreatedDateFromToken(token);
    return (
        username.equals(user.getUsername())
            && !isTokenExpired(token)
            && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
  }

  private static class ClaimsNotFoundException extends Exception {}
}

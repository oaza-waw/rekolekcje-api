package pl.oaza.warszawa.dor.rekolekcje.api.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.oaza.warszawa.dor.rekolekcje.api.security.users.Authority;
import pl.oaza.warszawa.dor.rekolekcje.api.security.users.User;

import java.util.List;
import java.util.stream.Collectors;

final class JwtUserFactory {

  private JwtUserFactory() {
  }

  static JwtUser create(User user) {
    return new JwtUser(
        user.getId(),
        user.getUsername(),
        user.getFirstname(),
        user.getLastname(),
        user.getPassword(),
        user.getEmail(),
        mapToGrantedAuthorities(user.getAuthorities()),
        user.getEnabled(),
        user.getLastPasswordResetDate()
    );
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
    return authorities.stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
        .collect(Collectors.toList());
  }
}

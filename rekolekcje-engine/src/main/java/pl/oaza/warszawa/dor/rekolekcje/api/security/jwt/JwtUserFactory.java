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
    return JwtUser.builder()
        .id(user.getId())
        .username(user.getUsername())
        .firstname(user.getFirstname())
        .lastname(user.getLastname())
        .password(user.getPassword())
        .email(user.getEmail())
        .authorities(mapToGrantedAuthorities(user.getAuthorities()))
        .enabled(user.getEnabled())
        .lastPasswordResetDate(user.getLastPasswordResetDate())
        .build();
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
    return authorities.stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
        .collect(Collectors.toList());
  }
}

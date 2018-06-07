package pl.oaza.warszawa.dor.rekolekcje.api.security.users;

import org.assertj.core.util.Lists;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AuthorityTest {

  @Test
  public void shouldCreateNewAuthority() {
    // given
    Authority admin = Authority.builder()
        .id(1L)
        .name(AuthorityName.ROLE_ADMIN)
        .users(Lists.newArrayList())
        .build();
    User user = User.builder()
        .id(11L)
        .username("username")
        .password("password")
        .firstname("Bill")
        .authorities(Lists.newArrayList())
        .build();

    // when
    admin.grantUserAccessToThisAuthority(user);

    // then
    assertThat(admin.getUsers()).containsOnly(user);
    assertThat(user.getAuthorities()).contains(admin);
  }
}

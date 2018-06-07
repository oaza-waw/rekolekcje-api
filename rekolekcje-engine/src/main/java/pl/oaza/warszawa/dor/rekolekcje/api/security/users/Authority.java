package pl.oaza.warszawa.dor.rekolekcje.api.security.users;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "AUTHORITY")
@Getter
@Builder
public class Authority {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "NAME", length = 50)
  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthorityName name;

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
  private List<User> users;

  void grantUserAccessToThisAuthority(User user) {
    users.add(user);
    user.getAuthorities().add(this);
  }
}

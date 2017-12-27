package pl.oaza.warszawa.dor.rekolekcje.api.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.oaza.warszawa.dor.rekolekcje.api.security.User;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}

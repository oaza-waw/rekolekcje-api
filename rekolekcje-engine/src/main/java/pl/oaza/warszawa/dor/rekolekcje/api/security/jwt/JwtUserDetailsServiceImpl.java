package pl.oaza.warszawa.dor.rekolekcje.api.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.oaza.warszawa.dor.rekolekcje.api.security.User;
import pl.oaza.warszawa.dor.rekolekcje.api.security.UserRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

//  @Autowired
//  public JwtUserDetailsServiceImpl(UserRepository userRepository) {
//    this.userRepository = userRepository;
//  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LOGGER.info("Loading user: " + username);
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
      LOGGER.info("Found user with name: " + user.getFirstname());
      return JwtUserFactory.create(user);
    }
  }
}

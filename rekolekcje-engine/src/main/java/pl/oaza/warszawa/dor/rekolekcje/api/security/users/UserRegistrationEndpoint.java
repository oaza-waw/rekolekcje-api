package pl.oaza.warszawa.dor.rekolekcje.api.security.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/users")
class UserRegistrationEndpoint {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationEndpoint.class);

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public UserRegistrationEndpoint(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/sign-up")
  public String signUp(@RequestBody User user) {
    if (userRepository.findByUsername(user.getUsername()) != null) {
      return "User already exists: " + user;
    }
    //TODO: create real user instead of a mock one
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEmail("email");
    user.setFirstname("first name");
    user.setLastname("last name");
    user.setEnabled(true);
    user.setLastPasswordResetDate(Date.valueOf(LocalDate.now().minusDays(1)));
    userRepository.save(user);
    LOGGER.info("Sign up successful for {}", user);
    return "Sign up successful. " + user;
  }
}

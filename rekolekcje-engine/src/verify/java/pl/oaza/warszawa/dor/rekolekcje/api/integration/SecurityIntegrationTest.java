package pl.oaza.warszawa.dor.rekolekcje.api.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.security.users.User;
import pl.oaza.warszawa.dor.rekolekcje.api.security.users.UserRepository;

public class SecurityIntegrationTest extends BaseIntegrationTest {

  private static final String API_URL = "/api";
  private static final String SIGN_UP_URL = API_URL + "/users/sign-up";
  private static final String AUTHORIZATION_URL = API_URL + "/auth";
  private static final String REFRESH_TOKEN_URL = API_URL + "/refresh";

  private static String username = "testuser";
  private static String password = "testpassword";

  @Autowired
  private UserRepository userRepository;

  @Before
  public void setup() throws Exception {
    super.setup();
  }

  private ResultActions registerNewUser(String username, String password) throws Exception {
    final String body = createRequestBody(username, password);
    final MockHttpServletRequestBuilder registerUserRequest = post(SIGN_UP_URL)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(body);
    return mockMvc.perform(registerUserRequest);
  }

  @Test
  public void shouldRegisterNewUser() throws Exception {
    registerNewUser(username, password);

    assertThat(userRepository.findByUsername(username)).isNotNull();
  }

  @Test
  public void shouldNotRegisterUserWithTheSameUsername() throws Exception {
    registerNewUser(username, password);

    final ResultActions response = registerNewUser(username, "secretsecret");

    response.andExpect(content().string("User already exists with this username: " + username));
  }

  @Test
  public void shouldRegisterNewUserOnlyOnce() throws Exception {
    final String someUsername = "someUsername";
    final String somePassword = "somePassword";

    registerNewUser(someUsername, somePassword);
    registerNewUser(someUsername, somePassword);

    assertThat(userRepository.findByUsername(someUsername)).isNotNull();
    final List<User> allUsersWithSpecificUsername = userRepository.findAll().stream()
        .filter(u -> u.getUsername().equals(someUsername))
        .collect(Collectors.toList());
    assertThat(allUsersWithSpecificUsername).hasSize(1);
  }

  @Test
  public void shouldReturnJwtWhenAuthorizedUserTriesToLogIn() throws Exception {
    registerNewUser(username, password);
    MvcResult result = requestAuthenticationToken(username, password);

    final String token = getTokenFromResponse(result);
    assertThat(token).isNotEmpty();

    final MockHttpServletRequestBuilder accessSecuredResourceRequest = get(API_URL + "/status")
        .header("Authorization", "Bearer " + token);
    mockMvc.perform(accessSecuredResourceRequest)
        .andExpect(status().isOk());
  }

  private MvcResult requestAuthenticationToken(String username, String password) throws Exception {
    final String body = createRequestBody(username, password);
    final MockHttpServletRequestBuilder getTokenRequest = post(AUTHORIZATION_URL)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(body);
    return mockMvc
        .perform(getTokenRequest)
        .andExpect(status().isOk())
        .andReturn();
  }

  private String getTokenFromResponse(MvcResult result) throws UnsupportedEncodingException {
    String response = result.getResponse().getContentAsString();
    JSONObject parser = new JSONObject(response);
    return parser.getString("token");
  }

  private String createRequestBody(String username, String password) {
    return "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
  }

  @Test
  public void shouldSendUnauthorizedResponseWhenAnonymousTriesToAccessSecuredData() throws Exception {
    mockMvc
        .perform(get(API_URL + "/status"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void shouldRefreshToken() throws Exception {
    registerNewUser(username, password);
    MvcResult authorizationResult = requestAuthenticationToken(username, password);

    final String oldToken = getTokenFromResponse(authorizationResult);
    assertThat(oldToken).isNotEmpty();

    final MockHttpServletRequestBuilder refreshTokenRequest = post(REFRESH_TOKEN_URL)
        .header("Authorization", oldToken);
    MvcResult refreshTokenResult = mockMvc.perform(refreshTokenRequest)
        .andExpect(status().isOk())
        .andReturn();

    final String refreshedToken = getTokenFromResponse(refreshTokenResult);
    assertThat(refreshedToken).isNotEqualTo(oldToken);
  }
}

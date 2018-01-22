package pl.oaza.warszawa.dor.rekolekcje.api.integration;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.oaza.warszawa.dor.rekolekcje.api.security.users.User;
import pl.oaza.warszawa.dor.rekolekcje.api.security.users.UserRepository;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityIntegrationTest {

  private static final String API_URL = "/api";
  private static final String SIGN_UP_URL = API_URL + "/users/sign-up";
  private static final String AUTHORIZATION_URL = API_URL + "/auth";
  private static final String REFRESH_TOKEN_URL = API_URL + "/refresh";

  private static String username = "testuser";
  private static String password = "testpassword";

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private UserRepository userRepository;

  private MockMvc mockMvc;

  public SecurityIntegrationTest() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .build();
    registerNewUser(username, password);
  }

  private void registerNewUser(String username, String password) throws Exception {
    final String body = createRequestBody(username, password);
    final MockHttpServletRequestBuilder registerUserRequest = post(SIGN_UP_URL)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(body);
    mockMvc.perform(registerUserRequest);
  }

  @Test
  public void shouldRegisterNewUser() throws Exception {
    registerNewUser(username, password);

    assertThat(userRepository.findByUsername(username)).isNotNull();
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

//  @Test
//  public void shouldAccessSecuredResourcesWhenAuthenticatingWithValidJwtToken() throws Exception {
//
//  }

//  @Test
//  @WithMockUser
//  public void shouldReturnOKStatus() throws Exception {
//    mockMvc
//        .perform(get(API_URL + "/status").with(user("user")))
//        .andExpect(status().isOk());
//  }
}

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

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityIntegrationTest {

  private static final String API_URL = "/api";
  private String username = "testuser";
  private String password = "testpassword";

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .build();
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

  private void registerNewUser(String username, String password) throws Exception {
    final String body = createRequestBody(username, password);
    final MockHttpServletRequestBuilder registerUserRequest = post("/users/sign-up")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(body);
    mockMvc.perform(registerUserRequest);
  }

  private MvcResult requestAuthenticationToken(String username, String password) throws Exception {
    final String body = createRequestBody(username, password);
    final MockHttpServletRequestBuilder getTokenRequest = post("/auth")
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

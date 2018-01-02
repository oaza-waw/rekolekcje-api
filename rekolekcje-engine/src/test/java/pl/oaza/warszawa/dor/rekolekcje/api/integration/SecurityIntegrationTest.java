package pl.oaza.warszawa.dor.rekolekcje.api.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.oaza.warszawa.dor.rekolekcje.api.security.UserRepository;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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
    registerNewUser(username, password);
  }

  private void registerNewUser(String username, String password) throws Exception {
    final String body = createRequestBody(username, password);
    final MockHttpServletRequestBuilder registerUserRequest = post("/users/sign-up")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(body);
    mockMvc.perform(registerUserRequest);
  }

  @Test
  public void shouldReturnJwtWhenAuthorizedUserTriesToLogIn() throws Exception {
    MvcResult result = getAuthenticationToken(username, password);

    String response = result.getResponse().getContentAsString();
    JSONObject parser = new JSONObject(response);
    final String token = parser.getString("token");
    assertThat(token).isNotEmpty();

    final MockHttpServletRequestBuilder accessSecuredResourceRequest = get(API_URL + "/status")
        .header("Authorization", "Bearer " + token);
    mockMvc.perform(accessSecuredResourceRequest)
        .andExpect(status().isOk());
  }

  private MvcResult getAuthenticationToken(String username, String password) throws Exception {
    final String body = createRequestBody(username, password);
    final MockHttpServletRequestBuilder getTokenRequest = post("/auth")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(body);
    return mockMvc
        .perform(getTokenRequest)
        .andExpect(status().isOk())
        .andReturn();
  }

  private String createRequestBody(String username, String password) {
    return "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
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
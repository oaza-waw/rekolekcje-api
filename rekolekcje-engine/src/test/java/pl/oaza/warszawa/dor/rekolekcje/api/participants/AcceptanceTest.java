package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AcceptanceTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    ConfigurableMockMvcBuilder mockMvcBuilder =
        MockMvcBuilders.webAppContextSetup(webApplicationContext);
    mockMvc = mockMvcBuilder.build();
  }

  @Test
  public void shouldReturnOKStatus() throws Exception {
    mockMvc
        .perform(get("/api/participants/status"))
        .andExpect(status().isOk());
  }
}

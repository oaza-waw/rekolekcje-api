package pl.oaza.warszawa.dor.rekolekcje.api.core;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles({"test", "test-local"})
@AutoConfigureJsonTesters
public abstract class BaseIntegrationTest {

  @Autowired
  protected JdbcTemplate jdbcTemplate;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper jsonMapper;

  protected Database database;

  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .build();
    database = new Database(jdbcTemplate);
  }
}

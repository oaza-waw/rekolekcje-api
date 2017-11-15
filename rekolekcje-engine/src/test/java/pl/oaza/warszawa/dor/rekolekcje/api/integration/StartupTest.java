package pl.oaza.warszawa.dor.rekolekcje.api.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StartupTest {

  @Autowired
  private ApplicationContext context;

  @Test
  public void shouldStartWithApplicationContext() {
    assertThat(context).isNotNull();
  }
}

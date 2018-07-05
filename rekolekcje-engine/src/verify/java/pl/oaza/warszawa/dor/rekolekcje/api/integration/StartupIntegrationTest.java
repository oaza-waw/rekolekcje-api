package pl.oaza.warszawa.dor.rekolekcje.api.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;

public class StartupIntegrationTest extends BaseIntegrationTest {

  @Autowired
  private ApplicationContext context;

  @Test
  public void shouldStartWithApplicationContext() {
    assertThat(context).isNotNull();
  }
}

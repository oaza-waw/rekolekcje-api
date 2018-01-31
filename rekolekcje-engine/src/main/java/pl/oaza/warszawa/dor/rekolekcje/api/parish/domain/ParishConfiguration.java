package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ParishConfiguration {

  @Bean
  ParishService parishService() {
    return new ParishService();
  }
}

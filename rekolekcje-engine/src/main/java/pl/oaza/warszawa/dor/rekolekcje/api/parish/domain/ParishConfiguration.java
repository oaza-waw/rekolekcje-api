package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ParishConfiguration {

  ParishService parishService() {
    return new ParishService(new InMemoryParishRepository());
  }

  @Bean
  ParishService parishService(ParishRepository parishRepository) {
    return new ParishService(parishRepository);
  }
}

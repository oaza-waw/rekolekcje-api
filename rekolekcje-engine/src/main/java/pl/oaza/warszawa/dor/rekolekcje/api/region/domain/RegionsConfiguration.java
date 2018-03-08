package pl.oaza.warszawa.dor.rekolekcje.api.region.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegionsConfiguration {

    RegionsService regionsService() {
      return new RegionsService(new InMemoryRegionsRepository());
    }

    @Bean
    RegionsService regionsService(RegionsRepository repository) {
      return new RegionsService(repository);
    }
}

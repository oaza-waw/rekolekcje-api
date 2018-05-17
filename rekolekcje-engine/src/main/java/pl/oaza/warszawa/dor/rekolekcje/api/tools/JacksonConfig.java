package pl.oaza.warszawa.dor.rekolekcje.api.tools;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JacksonConfig {

  private static final Logger logger = LoggerFactory.getLogger(JacksonConfig.class);

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
    return builder -> {
      builder.createXmlMapper(false).build();
      final JavaTimeModule javaTimeModule = new JavaTimeModule();
      builder.modules(javaTimeModule);
      builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      logger.info("Jackson mapper customized");
    };
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ParticipantsConfiguration {

  @Bean
  ParticipantsService participantsService(ParticipantsRepository participantsRepository, ParishRepository parishRepository) {
    return new ParticipantsService(participantsRepository, parishRepository);
  }
}

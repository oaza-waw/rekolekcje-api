package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ParticipantsConfiguration {

  ParticipantsService participantsService() {
    return new ParticipantsService(new InMemoryParticipantRepository());
  }

  @Bean
  ParticipantsService participantsService(ParticipantsRepository participantsRepository) {
    return new ParticipantsService(participantsRepository);
  }
}

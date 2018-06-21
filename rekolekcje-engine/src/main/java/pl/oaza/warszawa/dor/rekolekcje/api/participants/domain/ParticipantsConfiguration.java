package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ParticipantsConfiguration {

  ParticipantsFacade participantsService() {
    return participantsService(new InMemoryParticipantRepository());
  }

  @Bean
  ParticipantsFacade participantsService(ParticipantsRepository participantsRepository) {
    return new ParticipantsFacade(participantsRepository);
  }
}

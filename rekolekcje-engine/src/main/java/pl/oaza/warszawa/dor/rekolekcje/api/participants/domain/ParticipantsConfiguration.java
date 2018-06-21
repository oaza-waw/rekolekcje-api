package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ParticipantsConfiguration {

  ParticipantsFacade participantsFacade() {
    return participantsFacade(new InMemoryParticipantRepository());
  }

  @Bean
  ParticipantsFacade participantsFacade(ParticipantsRepository participantsRepository) {
    return new ParticipantsFacade(participantsRepository);
  }
}

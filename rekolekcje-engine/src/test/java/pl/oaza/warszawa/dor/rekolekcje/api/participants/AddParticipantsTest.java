package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddParticipantsTest extends ParticipantsTest {

  @Test
  public void shouldAddSingleParticipantToEmptySystem() {
    // given
    ParticipantDTO participantDTO = ParticipantDTO.builder("Jack", "Frost").build();

    // when
    ParticipantDTO addedParticipant = service.save(participantDTO);

    // then
    assertParticipantExistsInSystem(addedParticipant);
  }

  @Test
  public void shouldAddParticipantToSystemWithExistingParticipants() {
    // given
    ParticipantDTO existingParticipant = ParticipantDTO.builder("Sample", "Participant").build();
    saveAll(Collections.singletonList(existingParticipant));
    ParticipantDTO participantToAdd = ParticipantDTO.builder("Jack", "Frost").build();

    // when
    ParticipantDTO addedParticipant = service.save(participantToAdd);

    // then
    assertParticipantExistsInSystem(addedParticipant);
  }

  private void assertParticipantExistsInSystem(ParticipantDTO addedParticipant) {
    assertThat(addedParticipant).isNotNull();
    assertThat(addedParticipant.getId()).isNotZero();
    List<ParticipantDTO> allInSystem = getAllInSystem();
    assertThat(allInSystem).contains(addedParticipant);
  }
}

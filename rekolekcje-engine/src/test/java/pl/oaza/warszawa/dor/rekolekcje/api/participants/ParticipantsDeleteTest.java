package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantNotFoundException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsDeleteTest extends ParticipantsTest {

  private final ParticipantDTO firstParticipant = ParticipantDTO.builder().firstName("Roger").lastName("Moore").build();
  private final ParticipantDTO secondParticipant = ParticipantDTO.builder().firstName("Pierce").lastName("Brosnan").build();
  private final ParticipantDTO thirdParticipant = ParticipantDTO.builder().firstName("Sean").lastName("Connery").build();

  @Test
  public void shouldDeleteParticipantFromSystem() throws Exception {
    // given
    saveAll(Arrays.asList(firstParticipant, secondParticipant, thirdParticipant));
    ParticipantDTO participantToDelete = getCorrespondingParticipantFromSystem(secondParticipant);
    Long participantId = participantToDelete.getId();

    // when
    service.delete(participantId);

    // then
    assertThat(getAllInSystem()).doesNotContain(participantToDelete);
  }
}

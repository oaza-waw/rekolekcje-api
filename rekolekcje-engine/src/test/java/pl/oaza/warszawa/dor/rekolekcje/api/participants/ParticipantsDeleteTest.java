package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

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
    ParticipantDTO participantToDelete = getAllInSystem().stream()
        .findAny()
        .orElseThrow(ParticipantNotFoundException::new);
    long participantId = participantToDelete.getId();

    // when
    long deletedId = service.delete(participantId);

    // then
    assertThat(deletedId).isEqualTo(participantId);
    assertThat(getAllInSystem()).doesNotContain(participantToDelete);
  }
}

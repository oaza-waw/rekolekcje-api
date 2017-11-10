package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest extends ParticipantsTest {

  private final ParticipantDTO firstParticipant = ParticipantDTO.builder("Roger", "Moore").build();
  private final ParticipantDTO secondParticipant = ParticipantDTO.builder("Pierce", "Brosnan").build();
  private final ParticipantDTO thirdParticipant = ParticipantDTO.builder("Sean", "Connery").build();

  @Test
  public void shouldDeleteParticipantFromSystem() {
    // given
    saveAll(Arrays.asList(firstParticipant, secondParticipant, thirdParticipant));
    ParticipantDTO participantToDelete = getAllInSystem().stream().findAny().orElse(null);
    long participantId = participantToDelete.getId();

    // when
    long deletedId = service.delete(participantId);

    // then
    assertThat(deletedId).isEqualTo(participantId);
    assertThat(getAllInSystem()).doesNotContain(participantToDelete);
  }
}

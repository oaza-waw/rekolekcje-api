package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantFactory;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsDeleteTest extends ParticipantsTest {

  private final ParticipantDTO firstParticipant =
      ParticipantFactory.withSampleData("Roger", "Moore", "95030455412");

  private final ParticipantDTO secondParticipant =
      ParticipantFactory.withSampleData("Pierce", "Brosnan", "05230455412");

  private final ParticipantDTO thirdParticipant =
      ParticipantFactory.withSampleData("Sean", "Connery", "75093055412");

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

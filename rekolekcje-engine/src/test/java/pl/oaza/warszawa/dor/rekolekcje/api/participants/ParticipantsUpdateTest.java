package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsUpdateTest extends ParticipantsTest {

  private ParticipantDTO sampleParticipant1 =
      ParticipantFactory.withSampleData("Paul", "George", "90010112345");

  private ParticipantDTO sampleParticipant2 =
      ParticipantFactory.withSampleData("Roy", "Hibbert", "92010112345");

  private ParticipantDTO sampleParticipant3 =
      ParticipantFactory.withSampleData("George", "Hill", "93010112345");

  @Test
  public void shouldUpdateExistingParticipant() throws Exception {
    // given
    saveAll(ImmutableList.of(sampleParticipant1, sampleParticipant2, sampleParticipant3));
    ParticipantDTO existingParticipantWithOldData = getCorrespondingParticipantFromSystem(sampleParticipant2);
    ParticipantDTO participantWithUpdatedData =
        ParticipantFactory.withFullData(existingParticipantWithOldData.getId());

    // when
    ParticipantDTO participantAfterUpdate = service.update(participantWithUpdatedData);

    // then
    assertThat(participantAfterUpdate).isEqualTo(participantWithUpdatedData);
    ParticipantDTO participantInSystemWithTheSameId =
        getParticipantFromSystemWithTheSameId(participantWithUpdatedData);
    assertThat(participantInSystemWithTheSameId).isEqualTo(participantWithUpdatedData);
  }
}

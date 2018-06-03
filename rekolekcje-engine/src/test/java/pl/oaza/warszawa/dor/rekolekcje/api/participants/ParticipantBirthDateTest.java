package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantBirthDateTest extends ParticipantsTest {

  @Test
  public void shouldRetrievedParticipantHaveBirthDate(){
    //given
    ParticipantDTO participantDTO = ParticipantsTestData.participantWithFullData;

    //when
    ParticipantDTO addedParticipant = service.save(participantDTO);

    //then
    assertThat(addedParticipant.getPersonalData().getBirthDate()).isNotNull();
  }

  @Test
  public void shouldRetrievedParticipantHaveCorrectBirthDate(){
    //given
    ParticipantDTO participantDTO = ParticipantsTestData.participantWithMinimalData;
    String expectedBirthDate = "01.01.1993";

    //when
    ParticipantDTO addedParticipant = service.save(participantDTO);

    //then
    assertThat(addedParticipant.getPersonalData().getBirthDate()).isEqualTo(expectedBirthDate);
  }
}

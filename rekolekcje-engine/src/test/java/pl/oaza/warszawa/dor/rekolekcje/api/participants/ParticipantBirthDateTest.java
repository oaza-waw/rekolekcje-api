package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantFactory;

public class ParticipantBirthDateTest extends ParticipantsTest {

  @Test
  public void shouldRetrievedParticipantHaveBirthDate(){
    //given
    ParticipantDTO participantDTO = ParticipantFactory.withFullData(null);

    //when
    ParticipantDTO addedParticipant = facade.save(participantDTO);

    //then
    assertThat(addedParticipant.getPersonalData().getBirthDate()).isNotNull();
  }

  @Test
  public void shouldRetrievedParticipantHaveCorrectBirthDate(){
    //given
    ParticipantDTO participantDTO = ParticipantFactory.withMinimalData(null);
    String expectedBirthDate = "23.04.1992";

    //when
    ParticipantDTO addedParticipant = facade.save(participantDTO);

    //then
    assertThat(addedParticipant.getPersonalData().getBirthDate()).isEqualTo(expectedBirthDate);
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Ignore;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantBirthDateTest extends ParticipantsTest {

  @Test
  @Ignore
  public void shouldExistBirthDateInDto(){
    //given one participant with PESEL without birthday
    ParticipantDTO participantDTO = ParticipantsTestData.participantWithFullData;

    //when adding participant
    ParticipantDTO addedParticipant = service.save(participantDTO);

    //then returned participant has birth date
    assertThat(addedParticipant.getPersonalData().getBirthDate()).isNotNull();
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsDeleteTest extends ParticipantsTest {

  private final PersonalDataValue firstParticipantPersonalData =
      PersonalDataValue.builder()
          .firstName("Roger")
          .lastName("Moore")
          .pesel("95030455412")
          .build();
  private final PersonalDataValue secondParticipantPersonalData =
      PersonalDataValue.builder()
          .firstName("Pierce")
          .lastName("Brosnan")
          .pesel("05230455412")
          .build();

  private final PersonalDataValue thirdParticipantPersonalData =
      PersonalDataValue.builder()
          .firstName("Sean")
          .lastName("Connery")
          .pesel("75093055412")
          .build();

  private final ParticipantDTO firstParticipant =
      ParticipantDTO.builder()
          .personalData(firstParticipantPersonalData)
          .build();
  private final ParticipantDTO secondParticipant =
      ParticipantDTO.builder()
          .personalData(secondParticipantPersonalData)
          .build();
  private final ParticipantDTO thirdParticipant =
      ParticipantDTO.builder()
          .personalData(thirdParticipantPersonalData)
          .build();

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

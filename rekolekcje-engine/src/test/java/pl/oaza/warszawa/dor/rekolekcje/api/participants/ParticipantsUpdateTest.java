package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsUpdateTest extends ParticipantsTest {

  private ParticipantDTO sampleParticipant1 =
      ParticipantDTO.builder()
          .firstName("Paul")
          .lastName("George")
          .pesel(90010112345L)
          .address("Default City 23")
          .parishId(1L)
          .build();
  private ParticipantDTO sampleParticipant2 =
      ParticipantDTO.builder()
          .firstName("Roy")
          .lastName("Hibbert")
          .pesel(92010112345L)
          .address("Default City 42")
          .parishId(1L)
          .build();
  private ParticipantDTO sampleParticipant3 =
      ParticipantDTO.builder()
          .firstName("George")
          .lastName("Hill")
          .pesel(93010112345L)
          .address("Default City 11")
          .parishId(1L)
          .build();

  @Test
  public void shouldUpdateExistingParticipant() throws Exception {
    // given
    saveAll(Arrays.asList(sampleParticipant1, sampleParticipant2, sampleParticipant3));
    ParticipantDTO existingParticipantWithOldData = getCorrespondingParticipantFromSystem(sampleParticipant2);
    ParticipantDTO participantWithUpdatedData =
        ParticipantDTO.builder()
            .id(existingParticipantWithOldData.getId())
            .firstName("Danny")
            .lastName("Granger")
            .pesel(95010112345L)
            .address("New City 123")
            .parishId(1L)
            .fatherName("Father")
            .motherName("Mother")
            .christeningPlace("Christening address")
            .christeningDate(LocalDate.of(1995,12,3))
            .build();

    // when
    ParticipantDTO participantAfterUpdate = service.update(participantWithUpdatedData);

    // then
    assertThat(participantAfterUpdate).isEqualTo(participantWithUpdatedData);
    ParticipantDTO participantInSystemWithTheSameId =
        getParticipantFromSystemWithTheSameId(participantWithUpdatedData);
    assertThat(participantInSystemWithTheSameId).isEqualTo(participantWithUpdatedData);
  }
}

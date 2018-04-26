package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsUpdateTest extends ParticipantsTest {

  private AddressValue sampleAddress =
      AddressValue.builder()
          .city("Default City")
          .number(42)
          .street("Some street")
          .build();

  private ParticipantDTO sampleParticipant1 =
      ParticipantDTO.builder()
          .firstName("Paul")
          .lastName("George")
          .pesel(90010112345L)
          .parishId(1L)
          .build();
  private ParticipantDTO sampleParticipant2 =
      ParticipantDTO.builder()
          .firstName("Roy")
          .lastName("Hibbert")
          .pesel(92010112345L)
          .address(sampleAddress)
          .parishId(1L)
          .build();
  private ParticipantDTO sampleParticipant3 =
      ParticipantDTO.builder()
          .firstName("George")
          .lastName("Hill")
          .pesel(93010112345L)
          .address(sampleAddress)
          .parishId(1L)
          .build();

  @Test
  public void shouldUpdateExistingParticipant() throws Exception {
    // given
    saveAll(Arrays.asList(sampleParticipant1, sampleParticipant2, sampleParticipant3));
    ParticipantDTO existingParticipantWithOldData = getCorrespondingParticipantFromSystem(sampleParticipant2);
    PersonalData updatedPersonalData = PersonalData.builder()
        .fatherName("Father")
        .motherName("Mother")
        .christeningPlace("Christening address")
        .christeningDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")))
        .build();
    AddressValue updatedAddress = AddressValue.builder()
        .city("New City")
        .street("Brand new street")
        .number(432)
        .flat(11)
        .code("54-351")
        .build();
    ParticipantDTO participantWithUpdatedData =
        ParticipantDTO.builder()
            .id(existingParticipantWithOldData.getId())
            .firstName("Danny")
            .lastName("Granger")
            .pesel(95010112345L)
            .address(updatedAddress)
            .parishId(1L)
            .personalData(updatedPersonalData)
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

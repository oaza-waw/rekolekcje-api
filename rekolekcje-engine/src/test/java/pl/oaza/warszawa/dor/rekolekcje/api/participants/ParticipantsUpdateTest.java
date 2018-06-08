package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsUpdateTest extends ParticipantsTest {

  private AddressValue sampleAddress =
      AddressValue.builder()
          .city("Default City")
          .streetNumber(42)
          .streetName("Some street")
          .build();

  private PersonalDataValue samplePersonalData1 = PersonalDataValue.builder()
      .firstName("Paul")
      .lastName("George")
      .pesel("90010112345")
      .parishId(1L)
      .address(sampleAddress)
      .build();
  private PersonalDataValue samplePersonalData2 = PersonalDataValue.builder()
      .firstName("Roy")
      .lastName("Hibbert")
      .pesel("92010112345")
      .parishId(1L)
      .address(sampleAddress)
      .build();
  private PersonalDataValue samplePersonalData3 = PersonalDataValue.builder()
      .firstName("George")
      .lastName("Hill")
      .pesel("93010112345")
      .parishId(1L)
      .address(sampleAddress)
      .build();

  private ParticipantDTO sampleParticipant1 =
      ParticipantDTO.builder()
          .personalData(samplePersonalData1)
          .build();
  private ParticipantDTO sampleParticipant2 =
      ParticipantDTO.builder()
          .personalData(samplePersonalData2)
          .build();
  private ParticipantDTO sampleParticipant3 =
      ParticipantDTO.builder()
          .personalData(samplePersonalData3)
          .build();

  @Test
  public void shouldUpdateExistingParticipant() throws Exception {
    // given
    saveAll(Arrays.asList(sampleParticipant1, sampleParticipant2, sampleParticipant3));
    ParticipantDTO existingParticipantWithOldData = getCorrespondingParticipantFromSystem(sampleParticipant2);
    AddressValue updatedAddress = AddressValue.builder()
        .city("New City")
        .streetName("Brand new street")
        .streetNumber(432)
        .flatNumber(11)
        .postalCode("54-351")
        .build();
    PersonalDataValue updatedPersonalData = PersonalDataValue.builder()
        .firstName("Danny")
        .lastName("Granger")
        .pesel("95010112345")
        .address(updatedAddress)
        .fatherName("Father")
        .motherName("Mother")
        .parishId(1L)
        .christeningPlace("Christening address")
        .christeningDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")))
        .build();
    ParticipantDTO participantWithUpdatedData =
        ParticipantDTO.builder()
            .id(existingParticipantWithOldData.getId())
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

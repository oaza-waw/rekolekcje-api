package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ParticipantFactory {

  private static AddressValue sampleAddress = AddressValue.builder()
      .city("Chicago")
      .streetName("Narrow")
      .streetNumber(44)
      .build();

  private static final HealthReportValue sampleHealthStatus = HealthReportValue.builder()
      .currentTreatment("Standard treatment for diabetes")
      .medications("Insuline")
      .allergies("Peanuts, lactose")
      .other("May be very weird sometimes")
      .build();

  public static ParticipantDTO sampleParticipant(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("Sample")
        .lastName("Participant")
        .address(sampleAddress)
        .pesel("98101012345")
        .parishId(1L)
        .build();
  }

  public static ParticipantDTO participantWithMinimalData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("Minimal")
        .lastName("Participant")
        .pesel("92042312345")
        //.personalData(PersonalData.builder().birthDate("23.04.1992").build())
        .parishId(1L)
        .build();
  }

  public static ParticipantDTO participantWithAllData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("John")
        .lastName("Smith")
        .address(fullAddress())
        .pesel("90072312345")
        .parishId(1L)
        .personalData(fullPersonalData())
        .healthReport(sampleHealthStatus)
        .build();
  }

  private static PersonalData fullPersonalData() {
    return PersonalData.builder()
        .motherName("Mary")
        .fatherName("Jake")
        .birthDate("23.07.1990")
        .christeningPlace("Los Angeles")
        .christeningDate(ZonedDateTime.of(LocalDateTime.of(1981, 2, 13, 23, 0), ZoneId.of("UTC")))
        .emergencyContactName("Uncle Bob")
        .emergencyContactNumber(444555666L)
        .build();
  }

  private static AddressValue fullAddress() {
    return AddressValue.builder()
        .streetName("Broadway")
        .streetNumber(987)
        .flatNumber(13)
        .postalCode("12-654")
        .city("New York")
        .build();
  }
}

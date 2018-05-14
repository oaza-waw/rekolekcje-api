package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class ParticipantsTestData {

  private static final PersonalData samplePersonalData = PersonalData.builder()
      .motherName("Mary")
      .fatherName("Jake")
      .christeningPlace("Los Angeles")
      .christeningDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")))
      .emergencyContactName("Uncle Bob")
      .emergencyContactNumber(111222333L)
      .build();

  private static final AddressValue sampleAddress = AddressValue.builder()
      .city("New York")
      .postalCode("01-234")
      .streetName("42nd")
      .streetNumber(23)
      .flatNumber(125)
      .build();

  private static final AddressValue sampleAddress2 = AddressValue.builder()
      .city("Boston")
      .postalCode("01-423")
      .streetName("TD Garden")
      .streetNumber(23)
      .flatNumber(125)
      .build();

  private static final HealthReportValue sampleHealthReport = HealthReportValue.builder()
      .currentTreatment("Some theraphy")
      .medications("LSD")
      .allergies("Social interactions")
      .other("None")
      .build();

  private static final HealthReportValue sampleHealthReport2 = HealthReportValue.builder()
      .currentTreatment("Standard treatment for diabetes")
      .medications("Insuline")
      .allergies("Peanuts, lactose")
      .other("May be very weird sometimes")
      .build();

  static final ParticipantDTO participantWithMinimalData = ParticipantDTO.builder()
      .firstName("Jack")
      .lastName("Frost")
      .pesel(93010100000L)
      .build();

  private static final ExperienceValue sampleExperienceValue = ExperienceValue.builder()
      .kwcStatus("Member")
      .kwcSince(ZonedDateTime.of(LocalDateTime.of(1990, 5, 20, 4, 0), ZoneId.of("UTC")))
      .numberOfPrayerRetreats(2)
      .numberOfCommunionDays(3)
      .build();

  static final ParticipantDTO participantWithFullData = ParticipantDTO.builder()
      .firstName("Paul")
      .lastName("Pierce")
      .pesel(987654L)
      .parishId(1L)
      .address(sampleAddress)
      .personalData(samplePersonalData)
      .experience(sampleExperienceValue)
      .healthReport(sampleHealthReport)
      .build();

  static final ParticipantDTO sampleParticipant1 = ParticipantDTO.builder()
      .firstName("Kevin")
      .lastName("Garnett")
      .pesel(82020354321L)
      .address(sampleAddress)
      .build();

  static final ParticipantDTO sampleParticipant2 = ParticipantDTO.builder()
      .firstName("Ray")
      .lastName("Allen")
      .pesel(82020312345L)
      .address(sampleAddress2)
      .parishId(2L)
      .build();
}

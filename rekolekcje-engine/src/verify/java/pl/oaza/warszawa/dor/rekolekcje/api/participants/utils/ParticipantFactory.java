package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import com.google.common.collect.Sets;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class ParticipantFactory {

  private static final PersonalData samplePersonalData = PersonalData.builder()
      .christeningDate(ZonedDateTime.of(LocalDateTime.of(1991, 11, 21, 12, 0), ZoneId.of("UTC")))
      .motherName("Jane")
      .build();

  private static final AddressValue sampleAddress = AddressValue.builder()
      .city("Chicago")
      .build();

  private static final HealthReportValue sampleHealthReport = HealthReportValue.builder()
      .medications("Gripex")
      .build();

  private static final ExperienceValue sampleExperience = ExperienceValue.builder()
      .kwcStatus("Active")
      .build();

  public static ParticipantDTO sampleParticipant(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("Sample")
        .lastName("Participant")
        .personalData(samplePersonalData)
        .address(sampleAddress)
        .pesel("98101012345")
        .healthReport(sampleHealthReport)
        .experience(sampleExperience)
        .parishId(1L)
        .build();
  }

  public static ParticipantDTO participantWithMinimalData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("Minimal")
        .lastName("Participant")
        .pesel("92042312345")
        .personalData(PersonalData.builder()
            .birthDate("23.04.1992")
            .build())
        .parishId(1L)
        .build();
  }

  public static ParticipantDTO participantWithAllData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("John")
        .lastName("Smith")
        .address(fullAddress)
        .pesel("90042312345")
        .parishId(1L)
        .personalData(fullPersonalData)
        .experience(fullExperienceValue)
        .healthReport(fullHealthReport)
        .build();
  }

  private static PersonalData fullPersonalData = PersonalData.builder()
      .motherName("Mary")
      .fatherName("Jake")
      .birthDate("23.04.1990")
      .christeningPlace("Los Angeles")
      .christeningDate(ZonedDateTime.of(LocalDateTime.of(1981, 2, 13, 23, 0), ZoneId.of("UTC")))
      .emergencyContactName("Uncle Bob")
      .emergencyContactNumber(444555666L)
      .build();

  private static AddressValue fullAddress = AddressValue.builder()
      .streetName("Broadway")
      .streetNumber(987)
      .flatNumber(13)
      .postalCode("12-654")
      .city("New York")
      .build();

  private static final HealthReportValue fullHealthReport = HealthReportValue.builder()
      .currentTreatment("Standard treatment for diabetes")
      .medications("Insuline")
      .allergies("Peanuts, lactose")
      .other("May be very weird sometimes")
      .build();

  private static final Set<RetreatTurnValue> historicalTurns = Sets.newHashSet(
      RetreatTurnValue.builder()
          .stage("OND")
          .location("In the middle of nowhere")
          .year(1990)
          .build(),
      RetreatTurnValue.builder()
          .stage("ONŻ 1")
          .location("High Mountains")
          .year(2013)
          .build()
  );

  private static ExperienceValue fullExperienceValue = ExperienceValue.builder()
      .kwcStatus("Active")
      .kwcSince(ZonedDateTime.of(LocalDateTime.of(1995, 5, 4, 12, 0), ZoneId.of("UTC")))
      .numberOfCommunionDays(3)
      .numberOfPrayerRetreats(5)
      .leadingGroupToFormationStage("ONŻ I")
      .formationMeetingsInMonth(3)
      .celebrationsTaken(5)
      .celebrationsPlannedThisYear(5)
      .stepsTaken(4)
      .stepsPlannedThisYear(6)
      .deuterocatechumenateYear(2016)
      .historicalRetreats(historicalTurns)
      .build();
}

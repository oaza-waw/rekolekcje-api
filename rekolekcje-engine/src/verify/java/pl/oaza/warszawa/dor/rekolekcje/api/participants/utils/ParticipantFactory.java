package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import com.google.common.collect.Sets;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class ParticipantFactory {

  private static final PersonalDataValue minimalPersonalData =
      PersonalDataValue.builder()
          .firstName("Minimal")
          .lastName("Participant")
          .pesel("92042312345")
          .birthDate("23.04.1992")
          .parishId(1L)
          .build();

  public static ParticipantDTO participantWithMinimalData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .personalData(minimalPersonalData)
        .build();
  }

  public static ParticipantDTO sampleParticipant(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .personalData(samplePersonalData)
        .healthReport(sampleHealthReport)
        .experience(sampleExperience)
        .build();
  }

  private static final AddressValue sampleAddress = AddressValue.builder()
      .postalCode("AB 321")
      .build();

  private static final PersonalDataValue samplePersonalData = PersonalDataValue.builder()
      .firstName("Sample")
      .lastName("Participant")
      .pesel("98101012345")
      .parishId(1L)
      .address(sampleAddress)
      .christeningDate(ZonedDateTime.of(LocalDateTime.of(1991, 11, 21, 12, 0), ZoneId.of("UTC")))
      .birthDate("10.10.1998")
      .build();

  private static final HealthReportValue sampleHealthReport = HealthReportValue.builder()
      .currentTreatment("Broken leg")
      .build();

  private static final ExperienceValue sampleExperience = ExperienceValue.builder()
      .kwcStatus("Active")
      .build();

  private static final PersonalDataValue somePersonalData = PersonalDataValue.builder()
      .firstName("Han")
      .lastName("Solo")
      .pesel("81010154321")
      .parishId(1L)
      .address(sampleAddress)
      .christeningDate(ZonedDateTime.of(LocalDateTime.of(1991, 11, 21, 12, 0), ZoneId.of("UTC")))
      .build();

  public static ParticipantDTO sample() {
    return ParticipantDTO.builder()
        .personalData(somePersonalData)
        .build();
  }

  public static ParticipantDTO participantWithAllData() {
    return participantWithAllData(null);
  }

  public static ParticipantDTO participantWithAllData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .personalData(fullPersonalData)
        .experience(fullExperienceValue)
        .healthReport(fullHealthReport)
        .build();
  }

  private static AddressValue fullAddress = AddressValue.builder()
      .streetName("Broadway")
      .streetNumber(987)
      .flatNumber(13)
      .postalCode("12-654")
      .city("New York")
      .build();

  private static PersonalDataValue fullPersonalData = PersonalDataValue.builder()
      .firstName("John")
      .lastName("Smith")
      .pesel("90042312345")
      .parishId(1L)
      .address(fullAddress)
      .motherName("Mary")
      .fatherName("Jake")
      .birthDate("23.04.1990")
      .christeningPlace("Los Angeles")
      .christeningDate(ZonedDateTime.of(LocalDateTime.of(1981, 2, 13, 23, 0), ZoneId.of("UTC")))
      .emergencyContactName("Uncle Bob")
      .emergencyContactNumber(444555666L)
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

  public static ParticipantDTO copyWithDifferentId(ParticipantDTO original, long participantId, Set<RetreatTurnValue> retreatsWithIds) {
    final ExperienceValue newExperienceValue = copyExperienceWithRetreats(original.getExperience(), retreatsWithIds);
    return ParticipantDTO.builder()
        .id(participantId)
        .personalData(original.getPersonalData())
        .experience(newExperienceValue)
        .healthReport(original.getHealthReport())
        .build();
  }

  private static ExperienceValue copyExperienceWithRetreats(ExperienceValue original, Set<RetreatTurnValue> newRetreats) {
    return ExperienceValue.builder()
        .historicalRetreats(newRetreats)
        .kwcStatus(original.getKwcStatus())
        .kwcSince(original.getKwcSince())
        .numberOfCommunionDays(original.getNumberOfCommunionDays())
        .numberOfPrayerRetreats(original.getNumberOfPrayerRetreats())
        .deuterocatechumenateYear(original.getDeuterocatechumenateYear())
        .stepsTaken(original.getStepsTaken())
        .stepsPlannedThisYear(original.getStepsPlannedThisYear())
        .celebrationsTaken(original.getCelebrationsTaken())
        .celebrationsPlannedThisYear(original.getCelebrationsPlannedThisYear())
        .formationMeetingsInMonth(original.getFormationMeetingsInMonth())
        .leadingGroupToFormationStage(original.getLeadingGroupToFormationStage())
        .build();
  }

  public static ParticipantDTO withNewData(long id) {
    final AddressValue address = AddressValue.builder()
        .city("New City")
        .postalCode("AA-999")
        .build();
    final PersonalDataValue personalData = PersonalDataValue.builder()
        .firstName("Updated name")
        .lastName("Updated surname")
        .pesel("95040398765")
        .parishId(444L)
        .address(address)
        .motherName("Updated mother name")
        .christeningDate(ZonedDateTime.of(LocalDateTime.of(1950, 3, 21, 15, 0), ZoneId.of("UTC")))
        .birthDate("03.04.1995")
        .build();
    final ExperienceValue experience = ExperienceValue.builder()
        .kwcStatus("Updated status")
        .numberOfPrayerRetreats(3)
        .build();
    final HealthReportValue healthReport = HealthReportValue.builder()
        .currentTreatment("Updated treatment")
        .medications("Brand new medications")
        .build();
    return ParticipantDTO.builder()
        .id(id)
        .personalData(personalData)
        .experience(experience)
        .healthReport(healthReport)
        .build();
  }
}

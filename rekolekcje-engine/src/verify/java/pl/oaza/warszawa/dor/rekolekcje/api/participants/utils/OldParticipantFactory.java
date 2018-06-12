package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

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

public class OldParticipantFactory {

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

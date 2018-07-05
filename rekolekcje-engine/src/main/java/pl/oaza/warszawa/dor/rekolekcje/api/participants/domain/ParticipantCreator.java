package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import static pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.DateConverter.convertToDateTime;

import java.util.Set;
import java.util.stream.Collectors;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.CurrentApplicationValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

class ParticipantCreator {

  Participant from(ParticipantDTO participantDTO) {
    final Participant.ParticipantBuilder participantBuilder = Participant.builder()
        .id(participantDTO.getId());

    final PersonalData personalData = fromValue(participantDTO.getPersonalData());
    participantBuilder.personalData(personalData);

    final HealthReport healthReport = fromValue(participantDTO.getHealthReport());
    participantBuilder.healthReport(healthReport);

    final ExperienceValue experienceValue = participantDTO.getExperience();
    final Experience experience = fromValue(participantDTO.getExperience());
    participantBuilder.experience(experience);

    final CurrentApplication currentApplication = fromValue(participantDTO.getCurrentApplication());
    participantBuilder.currentApplication(currentApplication);

    final Participant participant = participantBuilder.build();

    if (experienceValue.getHistoricalRetreats() != null) {
      final Set<RetreatTurn> newHistoricalRetreats = extractHistoricalRetreats(experienceValue);
      final Set<RetreatTurn> historicalRetreats = participant.getExperience()
          .getHistoricalRetreats();
      historicalRetreats.clear();
      historicalRetreats.addAll(newHistoricalRetreats);
      participant.connectHistoricalTurnsWithParticipant();
    }

    return participant;
  }

  Participant withId(Participant participant, Long id) {
    return Participant.builder()
        .id(id)
        .personalData(participant.getPersonalData())
        .healthReport(participant.getHealthReport())
        .experience(participant.getExperience())
        .currentApplication(participant.getCurrentApplication())
        .build();
  }

  private PersonalData fromValue(PersonalDataValue personalDataValue) {
    return PersonalData.builder()
        .firstName(personalDataValue.getFirstName())
        .lastName(personalDataValue.getLastName())
        .pesel(personalDataValue.getPesel())
        .phoneNumber(personalDataValue.getPhoneNumber())
        .email(personalDataValue.getEmail())
        .parishId(personalDataValue.getParishId())
        .address(fromValue(personalDataValue.getAddress()))
        .motherName(personalDataValue.getMotherName())
        .fatherName(personalDataValue.getFatherName())
        .christeningPlace(personalDataValue.getChristeningPlace())
        .christeningDate(DateConverter.convertToDateTime(personalDataValue.getChristeningDate()))
        .emergencyContactName(personalDataValue.getEmergencyContactName())
        .emergencyContactNumber(personalDataValue.getEmergencyContactNumber())
        .schoolYear(personalDataValue.getSchoolYear())
        .nameDay(personalDataValue.getNameDay())
        .communityName(personalDataValue.getCommunityName())
        .build();
  }

  private Address fromValue(AddressValue addressValue) {
    return Address.builder()
        .street(addressValue.getStreetName())
        .streetNumber(addressValue.getStreetNumber())
        .flatNumber(addressValue.getFlatNumber())
        .postalCode(addressValue.getPostalCode())
        .city(addressValue.getCity())
        .build();
  }

  private HealthReport fromValue(HealthReportValue healthReportValue) {
    return HealthReport.builder()
        .currentTreatment(healthReportValue.getCurrentTreatment())
        .mentalDisorders(healthReportValue.getMentalDisorders())
        .medications(healthReportValue.getMedications())
        .allergies(healthReportValue.getAllergies())
        .medicalDiet(healthReportValue.getMedicalDiet())
        .canHike(healthReportValue.getCanHike())
        .illnessHistory(healthReportValue.getIllnessHistory())
        .hasMotionSickness(healthReportValue.getHasMotionSickness())
        .other(healthReportValue.getOther())
        .build();
  }

  private Experience fromValue(ExperienceValue experienceValue) {
    return Experience.builder()
        .kwcStatus(experienceValue.getKwcStatus())
        .kwcSince(convertToDateTime(experienceValue.getKwcSince()))
        .numberOfCommunionDays(experienceValue.getNumberOfCommunionDays())
        .numberOfPrayerRetreats(experienceValue.getNumberOfPrayerRetreats())
        .formationMeetingsInMonth(experienceValue.getFormationMeetingsInMonth())
        .leadingGroupToFormationStage(experienceValue.getLeadingGroupToFormationStage())
        .deuterocatechumenateYear(experienceValue.getDeuterocatechumenateYear())
        .stepsTaken(experienceValue.getStepsTaken())
        .stepsPlannedThisYear(experienceValue.getStepsPlannedThisYear())
        .celebrationsTaken(experienceValue.getCelebrationsTaken())
        .celebrationsPlannedThisYear(experienceValue.getCelebrationsPlannedThisYear())
        .build();
  }

  private Set<RetreatTurn> extractHistoricalRetreats(ExperienceValue experienceValue) {
    if (experienceValue.getHistoricalRetreats() == null) {
      return null;
    }

    return experienceValue.getHistoricalRetreats().stream()
        .map(this::fromValue)
        .collect(Collectors.toSet());
  }

  private RetreatTurn fromValue(RetreatTurnValue retreatTurnValue) {
    if (retreatTurnValue == null) {
      return RetreatTurn.builder().build();
    }

    return RetreatTurn.builder()
        .id(retreatTurnValue.getId())
        .stage(retreatTurnValue.getStage())
        .location(retreatTurnValue.getLocation())
        .year(retreatTurnValue.getYear())
        .build();
  }

  private CurrentApplication fromValue(CurrentApplicationValue value) {
    return CurrentApplication.builder()
        .stage(value.getStage())
        .turn(value.getTurn())
        .build();
  }

}

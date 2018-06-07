package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.DateConverter.convertToDateTime;

class ParticipantCreator {

  Participant from(ParticipantDTO participantDTO) {
    final Participant.ParticipantBuilder participantBuilder = Participant.builder()
        .id(participantDTO.getId())
        .firstName(participantDTO.getFirstName())
        .lastName(participantDTO.getLastName())
        .pesel(participantDTO.getPesel())
        .parishId(participantDTO.getParishId());

    final Address address = fromValue(participantDTO.getAddress());
    participantBuilder.address(address);

    final PersonalDataValue personalData = participantDTO.getPersonalData();
    participantBuilder
        .motherName(personalData.getMotherName())
        .fatherName(personalData.getFatherName())
        .christeningDate(convertToDateTime(personalData.getChristeningDate()))
        .christeningPlace(personalData.getChristeningPlace())
        .closeRelativeName(personalData.getEmergencyContactName())
        .closeRelativeNumber(personalData.getEmergencyContactNumber());

    final HealthReport healthReport = fromValue(participantDTO.getHealthReport());
    participantBuilder.healthReport(healthReport);

    final ExperienceValue experienceValue = participantDTO.getExperience();
    final Experience experience = fromValue(experienceValue);
    participantBuilder.experience(experience);

    final Participant participant = participantBuilder.build();

    if (experienceValue.getHistoricalRetreats() != null) {
      final Set<RetreatTurn> newHistoricalRetreats = extractHistoricalRetreats(experienceValue);
      final Set<RetreatTurn> historicalRetreats = participant.getExperience().getHistoricalRetreats();
      historicalRetreats.clear();
      historicalRetreats.addAll(newHistoricalRetreats);
      participant.connectHistoricalTurnsWithParticipant();
    }

    return participant;
  }

  Participant withId(Participant participant, Long id) {
    return Participant.builder()
        .id(id)
        .firstName(participant.getFirstName())
        .lastName(participant.getLastName())
        .pesel(participant.getPesel())
        .address(participant.getAddress())
        .parishId(participant.getParishId())
        .motherName(participant.getMotherName())
        .fatherName(participant.getFatherName())
        .christeningDate(participant.getChristeningDate())
        .christeningPlace(participant.getChristeningPlace())
        .closeRelativeName(participant.getCloseRelativeName())
        .closeRelativeNumber(participant.getCloseRelativeNumber())
        .healthReport(participant.getHealthReport())
        .experience(participant.getExperience())
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
    if (healthReportValue == null) return HealthReport.builder().build();

    return HealthReport.builder()
        .currentTreatment(healthReportValue.getCurrentTreatment())
        .medications(healthReportValue.getMedications())
        .allergies(healthReportValue.getAllergies())
        .other(healthReportValue.getOther())
        .build();
  }

  private Experience fromValue(ExperienceValue experienceValue) {
    if (experienceValue == null) {
      return Experience.builder().build();
    }
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

}

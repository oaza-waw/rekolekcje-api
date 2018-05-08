package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

class ParticipantCreator {
  Participant from(ParticipantDTO participantDTO) {
    final Participant.ParticipantBuilder participantBuilder = Participant.builder()
        .id(participantDTO.getId())
        .firstName(participantDTO.getFirstName())
        .lastName(participantDTO.getLastName())
        .pesel(participantDTO.getPesel())
        .parishId(participantDTO.getParishId());

    final Address address = from(participantDTO.getAddress());
    participantBuilder.address(address);

    final PersonalData personalData = participantDTO.getPersonalData();
    participantBuilder
        .motherName(personalData.getMotherName())
        .fatherName(personalData.getFatherName())
        .christeningDate(convertToDateTime(personalData.getChristeningDate()))
        .christeningPlace(personalData.getChristeningPlace())
        .closeRelativeName(personalData.getEmergencyContactName())
        .closeRelativeNumber(personalData.getEmergencyContactNumber());

    final HealthReport healthReport = from(participantDTO.getHealthReport());
    participantBuilder.healthReport(healthReport);

    final ExperienceValue experienceValue = participantDTO.getExperience();
    participantBuilder
        .kwcSince(convertToDateTime(experienceValue.getKwcSince()))
        .kwcStatus(experienceValue.getKwcStatus())
        .summerRetreats(getRetreatsEntry(participantDTO));

    return participantBuilder.build();
  }

  private LocalDateTime convertToDateTime(ZonedDateTime zonedDateTime) {
    return zonedDateTime != null ? zonedDateTime.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime() : null;
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
        .summerRetreats(participant.getSummerRetreats())
        .kwcStatus(participant.getKwcStatus())
        .kwcSince(participant.getKwcSince())
        .build();
  }

  Address from(AddressValue addressValue) {
    return Address.builder()
        .street(addressValue.getStreetName())
        .streetNumber(addressValue.getStreetNumber())
        .flatNumber(addressValue.getFlatNumber())
        .postalCode(addressValue.getPostalCode())
        .city(addressValue.getCity())
        .build();
  }

  HealthReport from(HealthReportValue healthReportValue) {
    if (healthReportValue == null) return HealthReport.builder().build();

    return HealthReport.builder()
        .currentTreatment(healthReportValue.getCurrentTreatment())
        .medications(healthReportValue.getMedications())
        .allergies(healthReportValue.getAllergies())
        .other(healthReportValue.getOther())
        .build();
  }

  List<Retreats> getRetreatsEntry(ParticipantDTO participantDTO) {
    final List<String> summerRetreats = participantDTO.getExperience().getSummerRetreats();
    if (summerRetreats == null) {
      return null;
    }
    return summerRetreats.stream()
        .map(retreatDescription -> Retreats.builder()
            .description(retreatDescription)
            .build())
        .collect(Collectors.toList());
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

class ParticipantCreator {
    Participant from(ParticipantDTO participantDTO) {
        return Participant.builder()
                .id(participantDTO.getId())
                .firstName(participantDTO.getFirstName())
                .lastName(participantDTO.getLastName())
                .pesel(participantDTO.getPesel())
                .address(participantDTO.getAddress())
                .parishId(participantDTO.getParishId())
                .motherName(participantDTO.getMotherName())
                .fatherName(participantDTO.getFatherName())
                .christeningPlace(participantDTO.getChristeningPlace())
                .christeningDate(convertToDateTime(participantDTO.getChristeningDate()))
                .currentTreatment(participantDTO.getCurrentTreatment())
                .medications(participantDTO.getMedications())
                .allergies(participantDTO.getAllergies())
                .other(participantDTO.getOther())
                .build();
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
                .currentTreatment(participant.getCurrentTreatment())
                .medications(participant.getMedications())
                .allergies(participant.getAllergies())
                .other(participant.getOther())
                .build();
    }
}

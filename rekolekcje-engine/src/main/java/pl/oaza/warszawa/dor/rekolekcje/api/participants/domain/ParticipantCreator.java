package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

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
        .christeningDate(participantDTO.getChristeningDate())
        .build();
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
        .build();
  }
}

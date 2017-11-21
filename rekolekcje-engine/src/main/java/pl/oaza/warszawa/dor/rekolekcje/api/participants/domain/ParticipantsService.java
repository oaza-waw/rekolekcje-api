package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantsService {

  private ParticipantsRepository participantsRepository;

  public ParticipantsService(ParticipantsRepository participantsRepository) {
    this.participantsRepository = participantsRepository;
  }

  public List<ParticipantDTO> findAll() {
    return participantsRepository.findAll().stream()
        .map(Participant::dto)
        .collect(Collectors.toList());
  }

  public ParticipantDTO find(long participantId) {
    return participantsRepository.findOne(participantId)
        .map(Participant::dto)
        .orElse(null);
  }

  public ParticipantDTO save(ParticipantDTO participantDTO) {
    return participantsRepository
        .save(new Participant(participantDTO))
        .dto();
  }

  public long delete(long participantId) {
    participantsRepository.delete(participantId);
    return participantId;
  }

  public ParticipantDTO update(ParticipantDTO participantWithUpdatedData) {
    return participantsRepository.save(new Participant(participantWithUpdatedData)).dto();
  }
}

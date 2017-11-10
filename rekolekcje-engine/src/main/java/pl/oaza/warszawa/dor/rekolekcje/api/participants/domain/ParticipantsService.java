package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.ArrayList;
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
}

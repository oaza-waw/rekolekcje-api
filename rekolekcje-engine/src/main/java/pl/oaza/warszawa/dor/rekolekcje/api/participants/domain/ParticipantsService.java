package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class ParticipantsService {

  private final ParticipantCreator participantCreator = new ParticipantCreator();
  private final ParticipantsRepository participantsRepository;

  public ParticipantsService(ParticipantsRepository participantsRepository) {
    this.participantsRepository = participantsRepository;
  }

  public List<ParticipantDTO> findAll() {
    return participantsRepository.findAll().stream()
        .map(Participant::dto)
        .collect(Collectors.toList());
  }

  public ParticipantDTO find(Long id) {
    requireNonNull(id);
    final Participant participant = participantsRepository.findOneOrThrow(id);
    return participant.dto();
  }

  public ParticipantDTO save(ParticipantDTO participantDTO) {
    requireNonNull(participantDTO);
    Participant participant = participantCreator.from(participantDTO);
    participant = participantsRepository.save(participant);
    return participant.dto();
  }

  public void delete(Long... ids) {
    requireNonNull(ids);
    Arrays.asList(ids)
        .forEach(participantsRepository::delete);
  }

  public ParticipantDTO update(ParticipantDTO participantWithUpdatedData) {
    requireNonNull(participantWithUpdatedData);
    Participant participant = participantCreator.from(participantWithUpdatedData);
    participant = participantsRepository.save(participant);
    return participant.dto();
  }

  public ParticipantDTO addBirthDate(ParticipantDTO participantDTO){
    ParticipantDTO participantWithBirthDate = ParticipantDTO.builder().build();
    return null;
  }
}

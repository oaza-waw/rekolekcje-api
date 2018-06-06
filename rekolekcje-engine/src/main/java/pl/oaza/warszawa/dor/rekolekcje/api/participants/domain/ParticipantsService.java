package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.springframework.transaction.annotation.Transactional;
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

  @Transactional
  public List<ParticipantDTO> findAll() {
    return participantsRepository.findAll().stream()
        .map(Participant::dto)
        .collect(Collectors.toList());
  }

  @Transactional
  public ParticipantDTO find(Long id) {
    requireNonNull(id);
    final Participant participant = participantsRepository.findOneOrThrow(id);
    return participant.dto();
  }

  @Transactional
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

  @Transactional
  public ParticipantDTO update(ParticipantDTO participantWithUpdatedData) {
    requireNonNull(participantWithUpdatedData);
    Participant participant = participantCreator.from(participantWithUpdatedData);
    participant = participantsRepository.save(participant);
    return participant.dto();
  }
}

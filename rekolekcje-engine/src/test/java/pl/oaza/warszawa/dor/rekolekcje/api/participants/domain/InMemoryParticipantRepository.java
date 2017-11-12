package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryParticipantRepository implements ParticipantsRepository {

  private ConcurrentHashMap<Long, Participant> participants = new ConcurrentHashMap<>();
  private Long nextId = 1L;

  ParticipantDTO save(ParticipantDTO dto) {
    return save(new Participant(dto)).dto();
  }

  @Override
  public Participant save(Participant entity) {
    return createParticipantToSave(entity);
  }

  @Override
  public Iterable<Participant> save(Collection<Participant> entities) {
    return entities.stream()
        .map(this::createParticipantToSave)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Participant> findOne(Long id) {
    return Optional.ofNullable(participants.get(id));
  }

  @Override
  public boolean exists(Long id) {
    return participants.containsKey(id);
  }

  @Override
  public List<Participant> findAll() {
    return new ArrayList<>(participants.values());
  }

  @Override
  public long count() {
    return participants.size();
  }

  @Override
  public void delete(Long id) {
    participants.remove(id);
  }

  @Override
  public void deleteAll() {
    participants.clear();
  }

  //TODO: rename method and/or refactor - it does more than just one thing
  private Participant createParticipantToSave(Participant entity) {
    Participant participant = new Participant(entity.dto());
    if (participants.containsKey(entity.getId())) {
      participants.put(participant.getId(), participant);
      return participant;
    }
    else {
      participant.setId(nextId);
      participants.put(nextId, participant);
      ++nextId;
      return participant;
    }
  }
}

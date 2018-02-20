package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

class InMemoryParticipantRepository implements ParticipantsRepository {

  private ConcurrentHashMap<Long, Participant> map = new ConcurrentHashMap<>();
  private Long nextId = 1L;
  private final ParticipantCreator participantCreator = new ParticipantCreator();

  @Override
  public Participant findOne(long id) {
    return map.get(id);
  }

  @Override
  public List<Participant> findAll() {
    return new ArrayList<>(map.values());
  }

  @Override
  public Participant save(Participant participant) {
    requireNonNull(participant);
    if (participant.getId() != null && map.containsKey(participant.getId())) {
      map.put(participant.getId(), participant);
      return participant;
    }
    else {
      Participant newParticipant = participantCreator.withId(participant, nextId);
      map.put(nextId, newParticipant);
      ++nextId;
      return newParticipant;
    }
  }

  @Override
  public void delete(long id) {
    map.remove(id);
  }
}

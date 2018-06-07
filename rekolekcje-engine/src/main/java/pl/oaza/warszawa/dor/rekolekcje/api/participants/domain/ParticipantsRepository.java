package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.springframework.data.repository.Repository;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantNotFoundException;

import java.util.List;

interface ParticipantsRepository extends Repository<Participant, Long> {

  Participant findOne(long id);
  List<Participant> findAll();
  Participant save(Participant participant);
  void delete(long id);

  default Participant findOneOrThrow(long id) {
    Participant participant = findOne(id);
    if (participant == null) {
      throw new ParticipantNotFoundException("No participant with id " + id + " found!");
    }
    return participant;
  }
}

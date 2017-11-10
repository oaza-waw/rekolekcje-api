package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

interface ParticipantsRepository extends Repository<Participant, Long> {

  Participant save(Participant entity);

  Iterable<Participant> save(Collection<Participant> entities);

  Optional<Participant> findOne(Long id);

  boolean exists(Long id);

  Collection<Participant> findAll();

  long count();

  void delete(Long id);

  void deleteAll();
}

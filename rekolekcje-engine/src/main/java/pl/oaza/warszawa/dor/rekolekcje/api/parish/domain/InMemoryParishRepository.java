package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

class InMemoryParishRepository {
  private ConcurrentHashMap<Long, Parish> map = new ConcurrentHashMap<>();

  public Parish findOne(long id) {
    return map.get(id);
  }

  public Parish findOneOrThrow(long id) {
    Parish parish = findOne(id);
    if (parish == null) {
      throw new ParishNotFoundException(id);
    }
    return parish;
  }

  public Parish save(Parish parish) {
    requireNonNull(parish);
    map.put(parish.dto().getId(), parish);
    return parish;
  }

  public List<Parish> findAll() {
    return new ArrayList<>(map.values());
  }

  public void delete(long id) {
    map.remove(id);
  }
}

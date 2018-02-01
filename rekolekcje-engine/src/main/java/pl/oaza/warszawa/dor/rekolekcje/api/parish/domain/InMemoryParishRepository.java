package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

class InMemoryParishRepository implements ParishRepository {
  private ConcurrentHashMap<Long, Parish> map = new ConcurrentHashMap<>();

  @Override
  public Parish findOne(long id) {
    return map.get(id);
  }

  @Override
  public List<Parish> findAll() {
    return new ArrayList<>(map.values());
  }

  @Override
  public Parish save(Parish parish) {
    requireNonNull(parish);
    map.put(parish.dto().getId(), parish);
    return parish;
  }

  @Override
  public void delete(long id) {
    map.remove(id);
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.region.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.requireNonNull;

class InMemoryRegionsRepository implements RegionsRepository {

  AtomicLong nextId = new AtomicLong(1);
  ConcurrentMap<Long, Region> data = new ConcurrentHashMap<>();

  @Override
  public List<Region> findAll() {
    return new ArrayList<>(data.values());
  }

  @Override
  public Region findOne(long id) {
    return data.get(id);
  }

  @Override
  public Region save(Region region) {
    requireNonNull(region);
    if (region.getId() == null || !data.containsKey(region.getId())) {
      region = region.withId(nextId.getAndIncrement());
    }
    data.put(region.getId(), region);
    return region;
  }

  @Override
  public void delete(long id) {
    data.remove(id);
  }
}

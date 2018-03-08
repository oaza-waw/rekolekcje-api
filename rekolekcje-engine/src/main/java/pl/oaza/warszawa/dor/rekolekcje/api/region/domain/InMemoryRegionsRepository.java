package pl.oaza.warszawa.dor.rekolekcje.api.region.domain;

import java.util.ArrayList;
import java.util.List;

class InMemoryRegionsRepository implements RegionsRepository {

  @Override
  public List<Region> findAll() {
    return new ArrayList<>();
  }
}

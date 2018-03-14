package pl.oaza.warszawa.dor.rekolekcje.api.region.domain;

import org.springframework.data.repository.Repository;
import pl.oaza.warszawa.dor.rekolekcje.api.region.dto.RegionNotFoundException;

import java.util.List;

public interface RegionsRepository extends Repository<Region, Long> {
  Region findOne(long id);
  List<Region> findAll();
  Region save(Region region);
  void delete(long id);
}

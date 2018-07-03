package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface RegionsRepository extends Repository<Region, Long> {
  Region findOne(long id);
  List<Region> findAll();
  Region save(Region region);
  void delete(long id);
}

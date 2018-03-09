package pl.oaza.warszawa.dor.rekolekcje.api.region.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.region.dto.RegionDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RegionsService {

  private final RegionsRepository repository;

  public RegionsService(RegionsRepository repository) {
    this.repository = repository;
  }

  public List<RegionDTO> findAll() {
    return repository.findAll().stream()
            .map(Region::dto)
            .collect(Collectors.toList());
  }
}

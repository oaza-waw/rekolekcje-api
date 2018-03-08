package pl.oaza.warszawa.dor.rekolekcje.api.region.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.region.dto.RegionDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RegionsService {

  private final RegionsRepository participantsRepository;

  public RegionsService(RegionsRepository participantsRepository) {
    this.participantsRepository = participantsRepository;
  }

  public List<RegionDTO> findAll() {
    return participantsRepository.findAll().stream()
            .map(Region::dto)
            .collect(Collectors.toList());
  }
}

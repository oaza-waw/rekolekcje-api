package pl.oaza.warszawa.dor.rekolekcje.api.region;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.oaza.warszawa.dor.rekolekcje.api.region.domain.RegionsService;
import pl.oaza.warszawa.dor.rekolekcje.api.region.dto.RegionDTO;

import java.util.List;

@RestController
public class RegionEndpoint {

  private final RegionsService service;

  public RegionEndpoint(RegionsService service) {
    this.service = service;
  }

  @GetMapping("/api/regions")
  public List<RegionDTO> getAllParticipants() {
    return service.findAll();
  }
}

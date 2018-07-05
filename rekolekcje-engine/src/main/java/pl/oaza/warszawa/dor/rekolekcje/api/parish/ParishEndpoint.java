package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.domain.ParishService;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.domain.RegionsService;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.RegionDTO;

@RestController
class ParishEndpoint {

  private ParishService parishService;
  private final RegionsService regionsService ;

  ParishEndpoint(ParishService parishService, RegionsService regionsService) {
    this.parishService = parishService;
    this.regionsService = regionsService;
  }

  @GetMapping("/api/parish")
  List<ParishDTO> allParishes() {
    return parishService.findAll();
  }

  @GetMapping("/api/parish/{id}")
  ParishDTO singleParish(@PathVariable Long id) {
    return parishService.findOne(id);
  }

  @PostMapping("/api/parish")
  ParishDTO createParish(@RequestBody ParishDTO newParish) {
    return parishService.save(newParish);
  }

  @DeleteMapping("/api/parish/{id}")
  void deleteParish(@PathVariable Long id) {
    parishService.delete(id);
  }

  @PutMapping("/api/parish")
  ParishDTO updateParish(@RequestBody ParishDTO parishWithNewData) {
    return parishService.save(parishWithNewData);
  }

  @GetMapping("/api/parish/regions")
  public List<RegionDTO> getAllRegions() {
    return regionsService.findAll();
  }

  @GetMapping("/api/parish/regions/{id}")
  public RegionDTO getOneRegion(@PathVariable Long id) {
    return regionsService.findOne(id);
  }

  @PostMapping("/api/parish/regions")
  public RegionDTO createRegion(@RequestBody RegionDTO newRegionData) {
    return regionsService.save(newRegionData);
  }

  @PutMapping("/api/parish/regions")
  public RegionDTO updateRegion(@RequestBody RegionDTO updatedRegionData) {
    return regionsService.save(updatedRegionData);
  }

  @DeleteMapping("/api/parish/regions/{id}")
  public void deleteRegion(@PathVariable Long id) {
    regionsService.delete(id);
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.domain.ParishService;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

import java.util.List;

@RestController
class ParishEndpoint {

  private ParishService parishService;

  ParishEndpoint(ParishService parishService) {
    this.parishService = parishService;
  }

  @GetMapping("/api/parish")
  private List<ParishDTO> allParishes() {
    return parishService.findAll();
  }

  @GetMapping("/api/parish/{id}")
  private ParishDTO singleParish(@PathVariable Long id) {
    return parishService.findOne(id);
  }

  @PostMapping("/api/parish")
  private ParishDTO createParish(@RequestBody ParishDTO newParish) {
    return parishService.add(newParish);
  }
}

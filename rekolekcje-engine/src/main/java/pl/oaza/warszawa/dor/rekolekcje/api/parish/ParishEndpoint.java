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
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

@RestController
class ParishEndpoint {

  private ParishService parishService;

  ParishEndpoint(ParishService parishService) {
    this.parishService = parishService;
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
}

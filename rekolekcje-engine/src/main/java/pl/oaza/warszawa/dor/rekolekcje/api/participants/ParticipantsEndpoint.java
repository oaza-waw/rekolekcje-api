package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ParticipantsEndpoint {

  @GetMapping("/api/participants/status")
  public String getStatus() {
    return "Connection successful";
  }
}

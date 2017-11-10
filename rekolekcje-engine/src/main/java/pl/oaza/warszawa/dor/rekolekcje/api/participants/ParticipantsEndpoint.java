package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsService;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;

@RestController
class ParticipantsEndpoint {

  private ParticipantsService participantsService;

  @Autowired
  ParticipantsEndpoint(ParticipantsService participantsService) {
    this.participantsService = participantsService;
  }

  @GetMapping("/api/participants/status")
  public String getStatus() {
    return "Connection successful";
  }

  @GetMapping("/api/participants")
  public List<ParticipantDTO> getAllParticipants() {
    return participantsService.findAll();
  }

  @GetMapping("/api/participants/{id}")
  public ParticipantDTO getSingleParticipant(@PathVariable long id) {
    return participantsService.find(id);
  }
}

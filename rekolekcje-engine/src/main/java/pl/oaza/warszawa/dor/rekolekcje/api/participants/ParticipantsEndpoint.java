package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsFacade;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

@RestController
class ParticipantsEndpoint {

  private ParticipantsFacade participantsFacade;

  @Autowired
  ParticipantsEndpoint(ParticipantsFacade participantsFacade) {
    this.participantsFacade = participantsFacade;
  }

  @GetMapping("/api/participants")
  public List<ParticipantDTO> getAllParticipants() {
    return participantsFacade.findAll();
  }

  @GetMapping("/api/participants/{id}")
  public ParticipantDTO getSingleParticipant(@PathVariable Long id) {
    return participantsFacade.find(id);
  }

  @PostMapping("/api/participants")
  @ResponseStatus(HttpStatus.CREATED)
  public ParticipantDTO saveParticipant(@RequestBody ParticipantDTO participantToSave) {
    return participantsFacade.save(participantToSave);
  }

  @DeleteMapping("/api/participants/{id}")
  public void deleteSingleParticipant(@PathVariable Long id) {
    participantsFacade.delete(id);
  }

  @PutMapping("/api/participants")
  public ParticipantDTO updateParticipant(@RequestBody ParticipantDTO participantToUpdate) {
    return participantsFacade.update(participantToUpdate);
  }
}

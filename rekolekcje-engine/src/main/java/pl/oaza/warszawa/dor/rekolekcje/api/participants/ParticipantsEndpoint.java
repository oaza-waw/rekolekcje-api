package pl.oaza.warszawa.dor.rekolekcje.api.participants;

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

  @GetMapping("/api/participants")
  public List<ParticipantDTO> getAllParticipants() {
    return participantsService.findAll();
  }

  @GetMapping("/api/participants/{id}")
  public ParticipantDTO getSingleParticipant(@PathVariable long id) {
    return participantsService.find(id);
  }

  @PostMapping("/api/participants")
  @ResponseStatus(HttpStatus.CREATED)
  public ParticipantDTO saveParticipant(@RequestBody ParticipantDTO participantToSave) {
    return participantsService.save(participantToSave);
  }

  @DeleteMapping("/api/participants/{id}")
  public long deleteSingleParticipant(@PathVariable long id) {
    return participantsService.delete(id);
  }

  @PutMapping("/api/participants")
  public ParticipantDTO updateParticipant(@RequestBody ParticipantDTO participantToUpdate) {
    return participantsService.update(participantToUpdate);
  }
}

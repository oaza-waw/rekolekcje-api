package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.junit.After;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ParticipantsTest {

  protected ParticipantsService service = new ParticipantsConfiguration().participantsService();

  protected List<ParticipantDTO> saveAll(List<ParticipantDTO> participantDTOs) {
    return participantDTOs.stream()
        .map(dto -> service.save(dto))
        .collect(Collectors.toList());
  }

  protected List<ParticipantDTO> getAllInSystem() {
    return service.findAll();
  }

  @After
  public void tearDown() {
    deleteAllParticipants();
  }

  private void deleteAllParticipants() {
    service.findAll().forEach(p -> service.delete(p.getId()));
  }
}

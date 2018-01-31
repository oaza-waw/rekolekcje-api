package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.junit.After;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ParticipantsTest {

  private InMemoryParticipantRepository repository = new InMemoryParticipantRepository();
  protected ParticipantsService service = new ParticipantsService(repository, null);

  protected List<ParticipantDTO> saveAll(List<ParticipantDTO> participantDTOs) {
    return participantDTOs.stream()
        .map(dto -> repository.save(dto))
        .collect(Collectors.toList());
  }

  protected List<ParticipantDTO> getAllInSystem() {
    return repository.findAll().stream()
        .map(Participant::dto)
        .collect(Collectors.toList());
  }

  @After
  public void tearDown() {
    clearRepository();
  }

  private void clearRepository() {
    repository.deleteAll();
  }

  public class ParticipantNotFoundException extends Exception {}
}

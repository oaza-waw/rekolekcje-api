package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.junit.After;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class ParticipantsTest {

  protected ParticipantsFacade service = new ParticipantsConfiguration().participantsService();

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

  protected ParticipantDTO getCorrespondingParticipantFromSystem(ParticipantDTO participantDTO) {
    return getAllInSystem().stream()
        .filter(p -> Objects.equals(p.getPersonalData().getPesel(), participantDTO.getPersonalData().getPesel()))
        .findAny()
        .orElseThrow(() ->
            new ParticipantNotFoundException("No participant with id " + participantDTO.getId() + " found!"));
  }

  protected ParticipantDTO getParticipantFromSystemWithTheSameId(ParticipantDTO participantDTO) {
    return getAllInSystem().stream()
        .filter(p -> Objects.equals(p.getId(), participantDTO.getId()))
        .findAny()
        .orElseThrow(() ->
            new ParticipantNotFoundException("No participant with id " + participantDTO.getId() + " found!"));
  }
}

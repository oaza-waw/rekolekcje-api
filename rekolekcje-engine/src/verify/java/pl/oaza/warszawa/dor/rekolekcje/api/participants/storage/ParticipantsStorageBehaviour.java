package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantNotFoundException;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParticipantsStorageBehaviour {

  private ParticipantsDatabase database;

  public ParticipantsStorageBehaviour(ParticipantsDatabase database) {
    this.database = database;
  }

  public void existParticipantsWithSampleData(List<ParticipantDTO> participants) {
    participants.forEach(dto -> database.persistPartialParticipantData(dto));
  }

  public void existsSingleParticipantWithSampleData(ParticipantDTO dto) {
    database.persistPartialParticipantData(dto);
  }

  public long participantWithTheSameDataIsFound(ParticipantDTO participant) throws ParticipantNotFoundException {
    return database.findIdOfParticipantWithTheSameData(participant);
  }

  public Set<Long> historicalRetreatsForParticipantAreFound(Long participantId) {
    return database.getAllRetreatTurnDataOfParticipant(participantId).stream()
        .map(RetreatTurnValue::getId)
        .collect(Collectors.toSet());
  }
}

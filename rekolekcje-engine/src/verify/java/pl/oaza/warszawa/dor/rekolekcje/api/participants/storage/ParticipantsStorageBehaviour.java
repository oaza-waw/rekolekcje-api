package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;

public class ParticipantsStorageBehaviour {

  private ParticipantsDatabase database;

  public ParticipantsStorageBehaviour(ParticipantsDatabase database) {
    this.database = database;
  }

  public void existSomeParticipants(List<ParticipantDTO> participants) {
    database.saveParticipants(participants);
  }
}

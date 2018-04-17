package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import pl.oaza.warszawa.dor.rekolekcje.api.core.Database;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;

public class ParticipantsStorageBehaviour {

    private Database database;

    public ParticipantsStorageBehaviour(Database database) {
        this.database = database;
    }

    public void existSomeParticipants(List<ParticipantDTO> participants) {
        database.saveParticipants(participants);
    }
}

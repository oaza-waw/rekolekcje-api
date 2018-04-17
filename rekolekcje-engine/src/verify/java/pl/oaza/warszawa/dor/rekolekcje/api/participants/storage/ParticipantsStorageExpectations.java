package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import pl.oaza.warszawa.dor.rekolekcje.api.core.Database;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsStorageExpectations {

    private final Database database;

    public ParticipantsStorageExpectations(Database database) {
        this.database = database;
    }

    public void numberOfParticipantsIsEqualTo(int quantity) {
        final List<ParticipantData> storedParticipantData = database.getAllParticipantData();
        assertThat(storedParticipantData).hasSize(quantity);

    }

    public ParticipantData participantExistsWithCorrectData(ParticipantDTO participant) {
        final ParticipantData savedParticipantData = database.getSavedParticipantData(participant);
        assertThat(savedParticipantData.getId()).isNotNull();
        assertThatDataIsTheSame(participant, savedParticipantData);
        return savedParticipantData;
    }

    public void participantNoLongerExists(long id) {
        final List<Long> idsInSystem = database.getAllParticipantData().stream()
                .map(ParticipantData::getId)
                .collect(toList());
        assertThat(idsInSystem).doesNotContain(id);
    }

    private void assertThatDataIsTheSame(ParticipantDTO dto, ParticipantData data) {
        assertThat(dto.getFirstName()).isEqualTo(data.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(data.getLastName());
        assertThat(dto.getAddress()).isEqualTo(data.getAddress());
        assertThat(dto.getPesel()).isEqualTo(data.getPesel());
        assertThat(dto.getParishId()).isEqualTo(data.getParishId());
        assertThat(dto.getFatherName()).isEqualTo(data.getFatherName());
        assertThat(dto.getMotherName()).isEqualTo(data.getMotherName());
        assertThat(dto.getChristeningPlace()).isEqualTo(data.getChristeningPlace());
        assertThat(dto.getChristeningDate()).isEqualTo(data.getChristeningDate());
    }
}

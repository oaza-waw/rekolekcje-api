package pl.oaza.warszawa.dor.rekolekcje.api.core;

import org.springframework.jdbc.core.JdbcTemplate;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.ParishData;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishNotFoundException;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantsDatabase;

import java.util.List;

public class Database {

  private JdbcTemplate jdbcTemplate;
  private ParticipantsDatabase participantsDatabase;

  Database(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.participantsDatabase = new ParticipantsDatabase(jdbcTemplate);
  }

  public List<ParishData> getAllParishData() {
    return jdbcTemplate.query("SELECT * FROM parish",
        (rs, rowNum) -> ParishData.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .address(rs.getString("address"))
            .build()
    );
  }

  public ParishData getSavedParishData(Long parishId) {
    List<ParishData> foundParishes = jdbcTemplate.query("SELECT id, name, address FROM parish WHERE id = ?",
        new Object[]{parishId},
        (rs, rowNum) -> ParishData.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .address(rs.getString("address"))
            .build()
    );
    return foundParishes.stream()
        .findAny()
        .orElseThrow(() -> new ParishNotFoundException(parishId));
  }

  public ParishData getSavedParishData(String parishName) {
    List<ParishData> foundParishes = jdbcTemplate.query("SELECT id, name, address FROM parish WHERE name = ?",
        new Object[]{parishName},
        (rs, rowNum) -> ParishData.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .address(rs.getString("address"))
            .build()
    );
    return foundParishes.stream()
        .findAny()
        .orElseThrow(() -> new ParishNotFoundException(0));
  }

  public void clearParish() {
    jdbcTemplate.execute("DELETE FROM parish");
  }

  public List<ParticipantData> getAllParticipantData() {
    return participantsDatabase.getAllParticipantData();
  }

  public ParticipantData getSavedParticipantData(Long id) {
    return participantsDatabase.getSavedParticipantData(id);
  }

  public ParticipantData getSavedParticipantData(ParticipantDTO participantDTO) {
    return participantsDatabase.getSavedParticipantData(participantDTO);
  }

  public void saveParticipants(List<ParticipantDTO> participantDTOs) {
    participantsDatabase.saveParticipants(participantDTOs);
  }

  public void clearParticipants() {
    participantsDatabase.clearParticipants();
  }
}


package pl.oaza.warszawa.dor.rekolekcje.api.core;

import org.springframework.jdbc.core.JdbcTemplate;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.ParishData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.ParticipantData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;

public class Database {

  private JdbcTemplate jdbcTemplate;

  Database(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
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
    return foundParishes.stream().findAny().orElseThrow(RuntimeException::new);
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
    return foundParishes.stream().findAny().orElseThrow(RuntimeException::new);
  }

  public List<ParticipantData> getAllParticipantData() {
    return jdbcTemplate.query("SELECT * FROM participant",
        (rs, rowNum) -> ParticipantData.builder()
            .id(rs.getLong("id"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .address(rs.getString("address"))
            .pesel(rs.getLong("pesel"))
            .parishId(rs.getLong("parish_id"))
            .build()
    );
  }

  public ParticipantData getSavedParticipantData(Long id) {
    List<ParticipantData> foundParticipants = jdbcTemplate.query("SELECT * FROM participant WHERE id = ?",
        new Object[]{id},
        (rs, rowNum) -> ParticipantData.builder()
            .id(rs.getLong("id"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .address(rs.getString("address"))
            .pesel(rs.getLong("pesel"))
            .parishId(rs.getLong("parish_id"))
            .build()
    );
    return foundParticipants.stream().findAny().orElseThrow(RuntimeException::new);
  }

  public ParticipantData getSavedParticipantData(String firstName, String lastName, Long pesel) {
    List<ParticipantData> foundParticipants = jdbcTemplate.query("SELECT * FROM participant WHERE first_name = ? AND last_name = ? AND pesel = ?",
        new Object[]{firstName, lastName, pesel},
        (rs, rowNum) -> ParticipantData.builder()
            .id(rs.getLong("id"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .address(rs.getString("address"))
            .pesel(rs.getLong("pesel"))
            .parishId(rs.getLong("parish_id"))
            .build()
    );
    return foundParticipants.stream().findAny().orElseThrow(RuntimeException::new);
  }

  public void saveParticipants(List<ParticipantDTO> participantDTOs) {
    participantDTOs.forEach(participant -> {
      jdbcTemplate.update("INSERT INTO participant(id, first_name, last_name, pesel, address, parish_id) VALUES (?, ?, ?, ?, ?, ?)",
          participant.getId(), participant.getFirstName(), participant.getLastName(), participant.getPesel(), participant.getAddress(), participant.getParishId());
    });
  }

  public void clearParticipants() {
    jdbcTemplate.execute("DELETE FROM participant");
  }

  public void clearParish() {
    jdbcTemplate.execute("DELETE FROM parish");
  }
}


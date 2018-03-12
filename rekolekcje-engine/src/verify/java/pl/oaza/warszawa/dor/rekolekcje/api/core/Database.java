package pl.oaza.warszawa.dor.rekolekcje.api.core;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.ParishData;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishNotFoundException;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.ParticipantData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.sql.Date;
import java.time.LocalDate;
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
        getParticipantDataRowMapper()
    );
    return foundParticipants.stream()
        .findAny()
        .orElseThrow(RuntimeException::new);
  }

  public ParticipantData getSavedParticipantData(ParticipantDTO participantDTO) {
    return getSavedParticipantData(participantDTO.getFirstName(),
        participantDTO.getLastName(),
        participantDTO.getPesel());
  }

  public ParticipantData getSavedParticipantData(String firstName, String lastName, Long pesel) {
    List<ParticipantData> foundParticipants = jdbcTemplate.query("SELECT * FROM participant WHERE first_name = ? AND last_name = ? AND pesel = ?",
        new Object[]{firstName, lastName, pesel},
        getParticipantDataRowMapper()
    );
    return foundParticipants.stream()
        .findAny()
        .orElseThrow(RuntimeException::new);
  }

  private RowMapper<ParticipantData> getParticipantDataRowMapper() {
    return (rs, rowNum) -> ParticipantData.builder()
        .id(rs.getLong("id"))
        .firstName(rs.getString("first_name"))
        .lastName(rs.getString("last_name"))
        .address(rs.getString("address"))
        .pesel(rs.getLong("pesel"))
        .parishId(rs.getLong("parish_id"))
        .fatherName(rs.getString("father_name"))
        .motherName(rs.getString("mother_name"))
        .christeningPlace(rs.getString("christening_place"))
        .christeningDate(extractLocalDate(rs.getDate("christening_date")))
        .build();
  }

  private LocalDate extractLocalDate(Date date) {
    return date != null ? date.toLocalDate() : null;
  }

  public void saveParticipants(List<ParticipantDTO> participantDTOs) {
    participantDTOs.forEach(dto -> {
      String fatherName = dto.getParents() != null ? dto.getParents().getFatherName() : null;
      String motherName = dto.getParents() != null ? dto.getParents().getMotherName() : null;
      String christeningPlace = dto.getChristening() != null ? dto.getChristening().getPlace() : null;
      LocalDate christeningDate = dto.getChristening() != null ? dto.getChristening().getDate() : null;
      jdbcTemplate.update("INSERT INTO participant(id, first_name, last_name, pesel, address, parish_id, father_name, mother_name, christening_place, christening_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
          dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getPesel(), dto.getAddress(), dto.getParishId(), fatherName, motherName, christeningPlace, christeningDate);
    });
  }

  public void clearParticipants() {
    jdbcTemplate.execute("DELETE FROM participant");
  }

  public void clearParish() {
    jdbcTemplate.execute("DELETE FROM parish");
  }
}


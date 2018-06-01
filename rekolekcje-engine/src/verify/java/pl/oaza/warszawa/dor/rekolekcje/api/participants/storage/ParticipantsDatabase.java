package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.oaza.warszawa.dor.rekolekcje.api.core.DaoTools;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantNotFoundException;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParticipantsDatabase {

  private JdbcTemplate jdbcTemplate;

  public ParticipantsDatabase(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  List<ParticipantSampleData> getAllParticipantData() {
    return jdbcTemplate.query("SELECT * FROM participant", participantSampleDataRowMapper());
  }

  public void clearParticipants() {
    jdbcTemplate.execute("DELETE FROM participant");
    jdbcTemplate.execute("DELETE FROM retreat_turn");
  }

  Long findIdOfParticipantWithTheSameData(ParticipantDTO participant) {
    final List<Long> foundIds = jdbcTemplate.query("SELECT id " +
            "FROM participant " +
            "WHERE first_name = ? AND last_name = ? AND pesel = ?",
        new Object[]{participant.getFirstName(), participant.getLastName(), participant.getPesel()},
        (rs, rowNum) -> rs.getLong("id")
    );
    return foundIds.stream()
        .findAny()
        .orElseThrow(() ->
            new ParticipantNotFoundException(participant));
  }

  ParticipantSampleData getPersistedData(ParticipantDTO dto) {
    final List<ParticipantSampleData> foundParticipants = jdbcTemplate.query("SELECT * " +
            "FROM participant " +
            "WHERE first_name = ? AND last_name = ? AND pesel = ?",
        new Object[]{dto.getFirstName(), dto.getLastName(), dto.getPesel()},
        participantSampleDataRowMapper()
    );
    return foundParticipants.stream()
        .findAny()
        .orElseThrow(() -> new ParticipantNotFoundException(dto));
  }

  private RowMapper<ParticipantSampleData> participantSampleDataRowMapper() {
    return (rs, rowNum) -> ParticipantSampleData.builder()
        .id(DaoTools.getLong(rs, "id"))
        .firstName(rs.getString("first_name"))
        .lastName(rs.getString("last_name"))
        .pesel(rs.getString("pesel"))
        .parishId(DaoTools.getLong(rs, "parish_id"))
        .christeningDate(DaoTools.getLocalDate(rs, "christening_date"))
        .postalCode(rs.getString("postal_code"))
        .currentTreatment(rs.getString("current_treatment"))
        .kwcStatus(rs.getString("kwc_status"))
        .numberOfCommunionDays(DaoTools.getInt(rs, "number_of_communion_days"))
        .build();
  }

  private Timestamp convertToLocalDate(ZonedDateTime zonedDateTime) {
    return zonedDateTime != null ? Timestamp.valueOf(zonedDateTime.toLocalDateTime()) : null;
  }

  void persistPartialParticipantData(ParticipantDTO dto) {
    jdbcTemplate.update("INSERT INTO participant (" +
            "id," +
            "first_name," +
            "last_name," +
            "pesel," +
            "parish_id," +
            "christening_date," +
            "postal_code," +
            "current_treatment," +
            "kwc_status)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
        dto.getId(),
        dto.getFirstName(),
        dto.getLastName(),
        dto.getPesel(),
        dto.getParishId(),
        convertToLocalDate(dto.getPersonalData().getChristeningDate()),
        dto.getAddress().getPostalCode(),
        dto.getHealthReport().getCurrentTreatment(),
        dto.getExperience().getKwcStatus());
  }

  Set<RetreatTurnValue> getAllRetreatTurnDataOfParticipant(long participantId) {
    return new HashSet<>(jdbcTemplate.query("SELECT * FROM retreat_turn WHERE participant_id = ?",
        new Object[]{participantId},
        retreatTurnValueRowMapper()
    ));
  }

  Set<RetreatTurnValue> getAllRetreatTurnData() {
    return new HashSet<>(jdbcTemplate.query("SELECT * FROM retreat_turn",
        retreatTurnValueRowMapper()
    ));
  }

  private RowMapper<RetreatTurnValue> retreatTurnValueRowMapper() {
    return (rs, rowNum) -> RetreatTurnValue.builder()
        .id(DaoTools.getLong(rs, "id"))
        .location(rs.getString("location"))
        .year(DaoTools.getInt(rs, "year"))
        .stage(rs.getString("stage"))
        .build();
  }
}

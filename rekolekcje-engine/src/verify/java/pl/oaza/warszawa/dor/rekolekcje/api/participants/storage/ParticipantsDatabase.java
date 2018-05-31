package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.oaza.warszawa.dor.rekolekcje.api.core.DaoTools;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantNotFoundException;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

public class ParticipantsDatabase {

  private JdbcTemplate jdbcTemplate;

  public ParticipantsDatabase(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  List<ParticipantData> getAllParticipantData() {
    return jdbcTemplate.query("SELECT * FROM participant", getParticipantDataRowMapper());
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
        .pesel(DaoTools.getLong(rs, "pesel"))
        .parishId(DaoTools.getLong(rs, "parish_id"))
        .christeningDate(DaoTools.getLocalDate(rs, "christening_date"))
        .postalCode(rs.getString("postal_code"))
        .currentTreatment(rs.getString("current_treatment"))
        .kwcStatus(rs.getString("kwc_status"))
        .numberOfCommunionDays(DaoTools.getInt(rs, "number_of_communion_days"))
        .build();
  }

  private RowMapper<ParticipantData> getParticipantDataRowMapper() {
    return (rs, rowNum) -> ParticipantData.builder()
        .id(DaoTools.getLong(rs, "id"))
        .firstName(rs.getString("first_name"))
        .lastName(rs.getString("last_name"))
        .pesel(rs.getString("pesel"))
        .parishId(DaoTools.getLong(rs, "parish_id"))
        .fatherName(rs.getString("father_name"))
        .motherName(rs.getString("mother_name"))
        .christeningPlace(rs.getString("christening_place"))
        .christeningDate(DaoTools.getLocalDate(rs, "christening_date"))
        .closeRelativeName(rs.getString("close_relative_name"))
        .closeRelativeNumber(DaoTools.getLong(rs, "close_relative_number"))
        .street(rs.getString("street"))
        .streetNumber(DaoTools.getInt(rs, "street_number"))
        .flatNumber(DaoTools.getInt(rs, "flat_number"))
        .postalCode(rs.getString("postal_code"))
        .city(rs.getString("city"))
        .currentTreatment(rs.getString("current_treatment"))
        .medications(rs.getString("medications"))
        .allergies(rs.getString("allergies"))
        .other(rs.getString("other"))
        .kwcStatus(rs.getString("kwc_status"))
        .kwcSince(DaoTools.getLocalDate(rs, "kwc_since"))
        .numberOfCommunionDays(DaoTools.getInt(rs, "number_of_communion_days"))
        .numberOfPrayerRetreats(DaoTools.getInt(rs, "number_of_prayer_retreats"))
        .leadingGroupToFormationStage(rs.getString("leading_group_to_formation_stage"))
        .formationMeetingsInMonth(DaoTools.getInt(rs, "formation_meetings_in_month"))
        .deuterocatechumenateYear(DaoTools.getInt(rs, "deuterocatechumenate_year"))
        .stepsTaken(DaoTools.getInt(rs, "steps_taken"))
        .stepsPlannedThisYear(DaoTools.getInt(rs, "steps_planned_this_year"))
        .celebrationsTaken(DaoTools.getInt(rs, "celebrations_taken"))
        .celebrationsPlannedThisYear(DaoTools.getInt(rs, "celebrations_planned_this_year"))
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
}

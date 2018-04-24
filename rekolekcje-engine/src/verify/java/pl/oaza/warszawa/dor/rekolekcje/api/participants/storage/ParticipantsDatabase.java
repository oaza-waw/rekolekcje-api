package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class ParticipantsDatabase {

  private JdbcTemplate jdbcTemplate;

  public ParticipantsDatabase(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<ParticipantData> getAllParticipantData() {
    return jdbcTemplate.query("SELECT * FROM participant", getParticipantDataRowMapper());
  }

  public ParticipantData getSavedParticipantData(ParticipantDTO participantDTO) {
    return getSavedParticipantData(participantDTO.getFirstName(),
        participantDTO.getLastName(),
        participantDTO.getPesel());
  }

  private ParticipantData getSavedParticipantData(String firstName, String lastName, Long pesel) {
    List<ParticipantData> foundParticipants = jdbcTemplate.query("SELECT * " +
            "FROM participant " +
            "WHERE first_name = ? " +
            "   AND last_name = ? " +
            "   AND pesel = ?",
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
        .christeningDate(extractLocalDate(rs.getTimestamp("christening_date")))
        .build();
  }

  private LocalDateTime extractLocalDate(Timestamp timestamp) {
    return timestamp != null ? timestamp.toLocalDateTime() : null;
  }

  public void saveParticipants(List<ParticipantDTO> participantDTOs) {
    participantDTOs.forEach(dto -> {
      jdbcTemplate.update(
          "INSERT INTO " +
              "participant(" +
              "id, " +
              "first_name, " +
              "last_name, " +
              "pesel, " +
              "address, " +
              "parish_id, " +
              "father_name, " +
              "mother_name, " +
              "christening_place, " +
              "christening_date" +
              ") " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
          dto.getId(),
          dto.getFirstName(),
          dto.getLastName(),
          dto.getPesel(),
          dto.getAddress(),
          dto.getParishId(),
          getFatherName(dto),
          getMotherName(dto),
          getChristeningPlace(dto),
          getChristeningDate(dto));
    });
  }

  private String getFatherName(ParticipantDTO dto) {
    return dto.getPersonalData() == null ? null : dto.getPersonalData().getFatherName();
  }

  private String getMotherName(ParticipantDTO dto) {
    return dto.getPersonalData() == null ? null : dto.getPersonalData().getMotherName();
  }

  private String getChristeningPlace(ParticipantDTO dto) {
    return dto.getPersonalData() == null ? null : dto.getPersonalData().getChristeningPlace();
  }

  private LocalDateTime getChristeningDate(ParticipantDTO dto) {
    if (dto.getPersonalData() == null) {
      return null;
    }
    final ZonedDateTime christeningDate = dto.getPersonalData().getChristeningDate();
    return christeningDate != null ? christeningDate.toLocalDateTime() : null;
  }

  public void clearParticipants() {
    jdbcTemplate.execute("DELETE FROM participant");
  }
}

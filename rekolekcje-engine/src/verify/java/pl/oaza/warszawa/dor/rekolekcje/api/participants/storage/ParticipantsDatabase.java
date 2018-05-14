package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import com.google.gag.annotation.remark.ThisWouldBeOneLineIn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.oaza.warszawa.dor.rekolekcje.api.core.DaoTools;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.time.LocalDateTime;
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
        .id(DaoTools.getLong(rs, "id"))
        .firstName(rs.getString("first_name"))
        .lastName(rs.getString("last_name"))
        .pesel(DaoTools.getLong(rs, "pesel"))
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
        .build();
  }

  void saveParticipants(List<ParticipantDTO> participantDTOs) {
    participantDTOs.forEach(dto -> {
      jdbcTemplate.update(
          "INSERT INTO " +
              "participant(" +
              "id, " +
              "first_name, " +
              "last_name, " +
              "pesel, " +
              "parish_id, " +
              "father_name, " +
              "mother_name, " +
              "christening_place, " +
              "christening_date, " +
              "close_relative_name, " +
              "close_relative_number, " +
              "street, " +
              "street_number, " +
              "flat_number, " +
              "postal_code, " +
              "city, " +
              "current_treatment, " +
              "medications, " +
              "allergies, " +
              "other," +
              "kwc_status, " +
              "kwc_since, " +
              "number_of_communion_days, " +
              "number_of_prayer_retreats " +
              ") " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
          dto.getId(),
          dto.getFirstName(),
          dto.getLastName(),
          dto.getPesel(),
          dto.getParishId(),
          getFatherName(dto),
          getMotherName(dto),
          getChristeningPlace(dto),
          convertToLocalDate(dto.getPersonalData().getChristeningDate()),
          dto.getPersonalData().getEmergencyContactName(),
          dto.getPersonalData().getEmergencyContactNumber(),
          dto.getAddress().getStreetName(),
          dto.getAddress().getStreetNumber(),
          dto.getAddress().getFlatNumber(),
          dto.getAddress().getPostalCode(),
          dto.getAddress().getCity(),
          dto.getHealthReport().getCurrentTreatment(),
          dto.getHealthReport().getMedications(),
          dto.getHealthReport().getAllergies(),
          dto.getHealthReport().getOther(),
          dto.getExperience().getKwcStatus(),
          convertToLocalDate(dto.getExperience().getKwcSince()),
          dto.getExperience().getNumberOfCommunionDays(),
          dto.getExperience().getNumberOfPrayerRetreats()
          );
    });
  }

  @ThisWouldBeOneLineIn(language = "groovy", toWit = "return dto?.getPersonalData()?.getFatherName()")
  private String getFatherName(ParticipantDTO dto) {
    return dto.getPersonalData() == null ? null : dto.getPersonalData().getFatherName();
  }

  private String getMotherName(ParticipantDTO dto) {
    return dto.getPersonalData() == null ? null : dto.getPersonalData().getMotherName();
  }

  private String getChristeningPlace(ParticipantDTO dto) {
    return dto.getPersonalData() == null ? null : dto.getPersonalData().getChristeningPlace();
  }

  private LocalDateTime convertToLocalDate(ZonedDateTime zonedDateTime) {
    return zonedDateTime != null ? zonedDateTime.toLocalDateTime() : null;
  }

  public void clearParticipants() {
    jdbcTemplate.execute("DELETE FROM participant");
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import com.google.gag.annotation.remark.ThisWouldBeOneLineIn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.oaza.warszawa.dor.rekolekcje.api.core.DaoTools;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantNotFoundException;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public class ParticipantsDatabase {

  private JdbcTemplate jdbcTemplate;

  public ParticipantsDatabase(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  List<ParticipantData> getAllParticipantData() {
    return jdbcTemplate.query("SELECT * FROM participant", getParticipantDataRowMapper());
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

  public ParticipantData getSavedParticipantData(ParticipantDTO participantDTO) {
    return getSavedParticipantData(participantDTO.getFirstName(),
        participantDTO.getLastName(),
        participantDTO.getPesel());
  }

  private ParticipantData getSavedParticipantData(String firstName, String lastName, String pesel) {
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

  void saveParticipants(List<ParticipantDTO> participantDTOs) {
    participantDTOs.forEach(dto -> {
      final Set<RetreatTurnValue> historicalRetreats = dto.getExperience().getHistoricalRetreats();
      if (historicalRetreats != null) {
        historicalRetreats.forEach(retreat -> {
          jdbcTemplate.update(
              "INSERT INTO retreat_turn(id, participant_id, stage, location, year)" +
                  "VALUES (?, ?, ?, ?, ?)",
              retreat.getId(), dto.getId(), retreat.getStage(), retreat.getLocation(), retreat.getYear()
          );
        });
      }
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
              "number_of_prayer_retreats, " +
              "FORMATION_MEETINGS_IN_MONTH, " +
              "LEADING_GROUP_TO_FORMATION_STAGE, " +
              "DEUTEROCATECHUMENATE_YEAR, " +
              "STEPS_TAKEN, " +
              "STEPS_PLANNED_THIS_YEAR, " +
              "CELEBRATIONS_TAKEN, " +
              "CELEBRATIONS_PLANNED_THIS_YEAR " +
              ") " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
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
          dto.getExperience().getNumberOfPrayerRetreats(),
          dto.getExperience().getFormationMeetingsInMonth(),
          dto.getExperience().getLeadingGroupToFormationStage(),
          dto.getExperience().getDeuterocatechumenateYear(),
          dto.getExperience().getStepsTaken(),
          dto.getExperience().getStepsPlannedThisYear(),
          dto.getExperience().getCelebrationsTaken(),
          dto.getExperience().getCelebrationsPlannedThisYear()
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

  private Timestamp convertToLocalDate(ZonedDateTime zonedDateTime) {
    return zonedDateTime != null ? Timestamp.valueOf(zonedDateTime.toLocalDateTime()) : null;
  }

  public void clearParticipants() {
    jdbcTemplate.execute("DELETE FROM participant");
    jdbcTemplate.execute("DELETE FROM retreat_turn");
  }

  void persistPartialParticipantsData(List<ParticipantDTO> participants) {
    participants.forEach(dto -> {
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
    });
  }
}

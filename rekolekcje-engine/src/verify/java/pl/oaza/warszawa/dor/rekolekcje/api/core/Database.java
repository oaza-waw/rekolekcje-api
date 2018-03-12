package pl.oaza.warszawa.dor.rekolekcje.api.core;

import org.springframework.jdbc.core.JdbcTemplate;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.ParishData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.ChristeningData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.ParentsData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.ParticipantData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParentsDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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

  public ParticipantData getSavedParticipantData(ParticipantDTO participantDTO) {
    return getSavedParticipantData(participantDTO.getFirstName(),
        participantDTO.getLastName(),
        participantDTO.getPesel());
  }

  public ParticipantData getSavedParticipantData(String firstName, String lastName, Long pesel) {
    List<ParticipantData> foundParticipants = jdbcTemplate.query("SELECT * FROM participant WHERE first_name = ? AND last_name = ? AND pesel = ?",
        new Object[]{firstName, lastName, pesel},
        (rs, rowNum) -> {
          ParentsData parentsData = getParentsData(rs.getString("father_name"),
              rs.getString("mother_name"));
          final Date date = rs.getDate("christening_date");
          ChristeningData christeningData = getChristeningData(date, rs.getString("christening_place"));
          return ParticipantData.builder()
              .id(rs.getLong("id"))
              .firstName(rs.getString("first_name"))
              .lastName(rs.getString("last_name"))
              .address(rs.getString("address"))
              .pesel(rs.getLong("pesel"))
              .parishId(rs.getLong("parish_id"))
              .parents(parentsData)
              .christening(christeningData)
              .build();
        }
    );
    return foundParticipants.stream().findAny().orElseThrow(RuntimeException::new);
  }

  private ParentsData getParentsData(String fatherName, String motherName) {
    return (fatherName != null || motherName != null) ?
        ParentsData.builder()
                .fatherName(fatherName)
                .motherName(motherName)
                .build()
        : null;
  }

  private ChristeningData getChristeningData(Date date, String place) {
    if (date == null && place == null) {
      return null;
    } else {
      final LocalDate christeningDate = date != null ? date.toLocalDate() : null;
      return ChristeningData.builder()
              .place(place)
              .date(christeningDate)
              .build();
    }
  }

  public void saveParticipants(List<ParticipantDTO> participantDTOs) {
    participantDTOs.forEach(participant -> {
      String fatherName = participant.getParents() != null ? participant.getParents().getFatherName() : null;
      String motherName = participant.getParents() != null ? participant.getParents().getMotherName() : null;
      String christeningPlace = participant.getChristening() != null ? participant.getChristening().getPlace() : null;
      LocalDate christeningDate = participant.getChristening() != null ? participant.getChristening().getDate() : null;
      jdbcTemplate.update("INSERT INTO participant(id, first_name, last_name, pesel, address, parish_id, father_name, mother_name, christening_place, christening_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
          participant.getId(), participant.getFirstName(), participant.getLastName(), participant.getPesel(), participant.getAddress(), participant.getParishId(), fatherName, motherName, christeningPlace, christeningDate);
    });
  }

  public void clearParticipants() {
    jdbcTemplate.execute("DELETE FROM participant");
  }

  public void clearParish() {
    jdbcTemplate.execute("DELETE FROM parish");
  }
}


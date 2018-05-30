package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsStorageExpectations {

  private final ParticipantsDatabase database;

  public ParticipantsStorageExpectations(ParticipantsDatabase database) {
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
    assertThat(dto.getPesel()).isEqualTo(data.getPesel());
    assertThat(dto.getParishId()).isEqualTo(data.getParishId());
    assertThat(dto.getPersonalData().getFatherName()).isEqualTo(data.getFatherName());
    assertThat(dto.getPersonalData().getMotherName()).isEqualTo(data.getMotherName());
    assertThat(dto.getPersonalData().getChristeningPlace()).isEqualTo(data.getChristeningPlace());
    compareDates(dto.getPersonalData().getChristeningDate(), convertToUtc(data.getChristeningDate()));
    assertThat(dto.getPersonalData().getEmergencyContactName()).isEqualTo(data.getCloseRelativeName());
    assertThat(dto.getPersonalData().getEmergencyContactNumber()).isEqualTo(data.getCloseRelativeNumber());
    assertThat(dto.getAddress().getStreetName()).isEqualTo(data.getStreet());
    assertThat(dto.getAddress().getStreetNumber()).isEqualTo(data.getStreetNumber());
    assertThat(dto.getAddress().getFlatNumber()).isEqualTo(data.getFlatNumber());
    assertThat(dto.getAddress().getPostalCode()).isEqualTo(data.getPostalCode());
    assertThat(dto.getAddress().getCity()).isEqualTo(data.getCity());
    compareDates(dto.getExperience().getKwcSince(), convertToUtc(data.getKwcSince()));
    assertThat(dto.getExperience().getKwcStatus()).isEqualTo(data.getKwcStatus());
    assertThat(dto.getExperience().getNumberOfCommunionDays()).isEqualTo(data.getNumberOfCommunionDays());
    assertThat(dto.getExperience().getNumberOfPrayerRetreats()).isEqualTo(data.getNumberOfPrayerRetreats());
  }

  private void compareDates(ZonedDateTime actual, ZonedDateTime expected) {
    if (actual == null && expected == null) {
      return;
    }
    assertThat(actual).isEqualTo(expected);
  }

  private ZonedDateTime convertToUtc(LocalDateTime localDateTime) {
    return localDateTime != null ? ZonedDateTime.of(localDateTime, ZoneId.of("UTC")) : null;
  }

  public void correctDataIsPersisted(ParticipantDTO dto) {
    final ParticipantSampleData persistedData = database.getPersistedData(dto);
    assertThat(persistedData.getId()).isNotNull();
    assertThat(persistedData.getFirstName()).isEqualTo(dto.getFirstName());
    assertThat(persistedData.getLastName()).isEqualTo(dto.getLastName());
    assertThat(persistedData.getPesel()).isEqualTo(dto.getPesel());
    assertThat(persistedData.getParishId()).isEqualTo(dto.getParishId());
    compareDates(dto.getPersonalData().getChristeningDate(), convertToUtc(persistedData.getChristeningDate()));
    assertThat(persistedData.getPostalCode()).isEqualTo(dto.getAddress().getPostalCode());
    assertThat(persistedData.getCurrentTreatment()).isEqualTo(dto.getHealthReport().getCurrentTreatment());
    assertThat(persistedData.getKwcStatus()).isEqualTo(dto.getExperience().getKwcStatus());
    assertThat(persistedData.getNumberOfCommunionDays()).isEqualTo(dto.getExperience().getNumberOfCommunionDays());
  }
}

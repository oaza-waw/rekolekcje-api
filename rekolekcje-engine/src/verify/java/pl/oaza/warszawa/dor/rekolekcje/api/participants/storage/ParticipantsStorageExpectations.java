package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsStorageExpectations {

  private final ParticipantsDatabase database;

  public ParticipantsStorageExpectations(ParticipantsDatabase database) {
    this.database = database;
  }

  public void numberOfParticipantsIsEqualTo(int quantity) {
    final List<ParticipantSampleData> storedParticipantData = database.getAllParticipantData();
    assertThat(storedParticipantData).hasSize(quantity);

  }

  public void participantNoLongerExists(long id) {
    final List<Long> idsInSystem = database.getAllParticipantData().stream()
        .map(ParticipantSampleData::getId)
        .collect(toList());
    assertThat(idsInSystem).doesNotContain(id);
  }

  public void correctDataIsPersisted(ParticipantDTO dto) {
    final ParticipantSampleData persistedData = database.getPersistedData(dto);
    assertThat(persistedData.getId()).isNotNull();
    assertThat(persistedData.getFirstName()).isEqualTo(dto.getPersonalData().getFirstName());
    assertThat(persistedData.getLastName()).isEqualTo(dto.getPersonalData().getLastName());
    assertThat(persistedData.getPesel()).isEqualTo(dto.getPersonalData().getPesel());
    assertThat(persistedData.getParishId()).isEqualTo(dto.getPersonalData().getParishId());
    compareDates(dto.getPersonalData().getChristeningDate(), convertToUtc(persistedData.getChristeningDate()));
    assertThat(persistedData.getPostalCode()).isEqualTo(dto.getAddress().getPostalCode());
    assertThat(persistedData.getCurrentTreatment()).isEqualTo(dto.getHealthReport().getCurrentTreatment());
    assertThat(persistedData.getKwcStatus()).isEqualTo(dto.getExperience().getKwcStatus());
    assertThat(persistedData.getNumberOfCommunionDays()).isEqualTo(dto.getExperience().getNumberOfCommunionDays());
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

  public Set<RetreatTurnValue> historicalRetreatsHaveIds(long participantId) {
    final Set<RetreatTurnValue> historicalRetreats = database.getAllRetreatTurnDataOfParticipant(participantId);
    if (!historicalRetreats.isEmpty()) {
      Set<Long> ids = historicalRetreats.stream()
          .map(RetreatTurnValue::getId)
          .filter(Objects::nonNull)
          .collect(Collectors.toSet());
      assertThat(ids).isNotNull();
    }
    return historicalRetreats;
  }

  public void historicalRetreatsNoLongerExist(Set<Long> retreatsIds) {
    final Set<Long> allPersistedRetreatsIds = database.getAllRetreatTurnData().stream()
        .map(RetreatTurnValue::getId)
        .collect(Collectors.toSet());
    assertThat(allPersistedRetreatsIds).doesNotContainAnyElementsOf(retreatsIds);
  }
}

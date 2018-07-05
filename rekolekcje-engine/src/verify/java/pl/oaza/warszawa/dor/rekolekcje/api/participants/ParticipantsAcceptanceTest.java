package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.storage.ParticipantsDatabase;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.storage.ParticipantsStorageBehaviour;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.storage.ParticipantsStorageExpectations;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantFactory;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantsApiBehaviour;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantsApiExpectations;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

public class ParticipantsAcceptanceTest extends BaseIntegrationTest {

  private ParticipantsStorageBehaviour whenInStorage;
  private ParticipantsStorageExpectations thenInStorage;

  private ParticipantsApiBehaviour whenInParticipantsApi;
  private ParticipantsApiExpectations thenInParticipantsApi;

  private final ParticipantDTO participantWithMinimalData = ParticipantFactory.withMinimalData(1L);
  private final ParticipantDTO participantWithSampleData = ParticipantFactory.withSampleData(2L);
  private final ParticipantDTO participantWithFullData = ParticipantFactory.withFullData(null);

  private final List<ParticipantDTO> participants = ImmutableList.of(participantWithMinimalData, participantWithSampleData);

  @Before
  public void setup() throws Exception {
    super.setup();

    ParticipantsDatabase participantsDatabase = new ParticipantsDatabase(jdbcTemplate);

    whenInStorage = new ParticipantsStorageBehaviour(participantsDatabase);
    thenInStorage = new ParticipantsStorageExpectations(participantsDatabase);

    whenInParticipantsApi = new ParticipantsApiBehaviour(mockMvc, jsonMapper);
    thenInParticipantsApi = new ParticipantsApiExpectations(jsonMapper);

    participantsDatabase.clearParticipants();
  }

  @Test
  @WithMockUser
  public void shouldGetAllParticipantsWhenTheyAreAlreadySaved() throws Exception {
    whenInStorage.existParticipantsWithSampleData(participants);

    final ResultActions response = whenInParticipantsApi.allParticipantsAreRequested();

    thenInParticipantsApi.responseHasAllParticipants(response, participants);
  }

  @Test
  @WithMockUser
  public void shouldGetOneParticipantWhenHeIsAlreadySaved() throws Exception {
    whenInStorage.existParticipantsWithSampleData(participants);

    final ResultActions response =
        whenInParticipantsApi.singleParticipantIsRequested(participantWithSampleData.getId());

    thenInParticipantsApi.okResponseHasCorrectParticipantData(response, participantWithSampleData);
  }

  @Test
  @WithMockUser
  public void shouldReturnFullDataOfASingleParticipant() throws Exception {
    whenInParticipantsApi.singleParticipantIsAdded(participantWithFullData);
    final long id = whenInStorage.participantWithTheSameDataIsFound(participantWithFullData);

    final ResultActions response =
        whenInParticipantsApi.singleParticipantIsRequested(id);

    final Set<RetreatTurnValue> retreatsWithIds = thenInStorage.historicalRetreatsHaveIds(id);
    final ParticipantDTO expectedParticipant =
        ParticipantFactory.from(participantWithFullData)
            .withId(id)
            .withHistoricalRetreats(retreatsWithIds)
            .clone();
    thenInParticipantsApi.okResponseHasCorrectParticipantData(response, expectedParticipant);
  }

  @Test
  @WithMockUser
  public void shouldReturnCorrectDataWhenNewParticipantIsAdded() throws Exception {
    whenInStorage.existParticipantsWithSampleData(participants);

    final ResultActions response = whenInParticipantsApi.singleParticipantIsAdded(participantWithFullData);

    final long id = whenInStorage.participantWithTheSameDataIsFound(participantWithFullData);
    final Set<RetreatTurnValue> retreatsWithIds = thenInStorage.historicalRetreatsHaveIds(id);
    final ParticipantDTO expectedParticipant =
        ParticipantFactory.from(participantWithFullData)
            .withId(id)
            .withHistoricalRetreats(retreatsWithIds)
            .clone();
    thenInParticipantsApi.createdResponseHasCorrectParticipantData(response, expectedParticipant);
  }

  @Test
  @WithMockUser
  public void shouldSetParticipantIdWhenANewOneIsAdded() throws Exception {
    whenInStorage.existParticipantsWithSampleData(participants);

    final ResultActions response = whenInParticipantsApi.singleParticipantIsAdded(participantWithFullData);

    thenInParticipantsApi.responseHasParticipantWithId(response);
  }

  @Test
  @WithMockUser
  public void shouldPersistParticipantWhenHeIsAdded() throws Exception {
    whenInStorage.existParticipantsWithSampleData(participants);

    final ParticipantDTO sampleParticipant =
        ParticipantFactory.withSampleData("Han", "Solo", "81010154321");
    whenInParticipantsApi.singleParticipantIsAdded(sampleParticipant);

    thenInStorage.numberOfParticipantsIsEqualTo(participants.size() + 1);
    thenInStorage.correctDataIsPersisted(sampleParticipant);
  }

  @Test
  @WithMockUser
  public void shouldReturnCorrectDataWhenMultipleParticipantsAreAdded() throws Exception {
    // given some participants are already in database
    // when multiple participants are added at once
    // then all their data is returned with ids
  }

  @Test
  @WithMockUser
  public void shouldPersistAllParticipantsWhenMultipleAreAdded() throws Exception {
    // given some participants are already in database
    // when multiple participants are added
    // then their data is persisted
  }

  @Test
  @WithMockUser
  public void shouldDeleteASingleParticipant() throws Exception {
    whenInStorage.existsSingleParticipantWithSampleData(participantWithSampleData);
    final Long id = participantWithSampleData.getId();

    final ResultActions response =
        whenInParticipantsApi.singleParticipantIsDeleted(id);

    thenInParticipantsApi.okStatusIsReturned(response);
    thenInStorage.participantNoLongerExists(id);
  }

  @Test
  @WithMockUser
  public void shouldDeleteParticipantWithItsHistoricalRetreats() throws Exception {
    whenInParticipantsApi.singleParticipantIsAdded(participantWithFullData);
    final Long participantId = whenInStorage.participantWithTheSameDataIsFound(participantWithFullData);
    final Set<Long> retreatsIds = whenInStorage.historicalRetreatsForParticipantAreFound(participantId);

    whenInParticipantsApi.singleParticipantIsDeleted(participantId);

    thenInStorage.historicalRetreatsNoLongerExist(retreatsIds);
  }

  @Test
  @WithMockUser
  public void shouldReturnParticipantWithNewDataWhenSingleOneIsUpdated() throws Exception {
    whenInStorage.existsSingleParticipantWithSampleData(participantWithSampleData);
    final Long id = participantWithSampleData.getId();

    final ParticipantDTO participantWithNewData = ParticipantFactory.withUpdatedData(id);
    final ResultActions response = whenInParticipantsApi.singleParticipantIsUpdated(participantWithNewData);

    thenInParticipantsApi.okResponseHasCorrectParticipantData(response, participantWithNewData);
  }

  @Test
  @WithMockUser
  public void shouldUpdatePersistedDataWhenSingleParticipantIsUpdated() throws Exception {
    whenInStorage.existParticipantsWithSampleData(participants);
    final Long id = participantWithSampleData.getId();

    final ParticipantDTO participantWithNewData = ParticipantFactory.withUpdatedData(id);
    whenInParticipantsApi.singleParticipantIsUpdated(participantWithNewData);

    thenInStorage.numberOfParticipantsIsEqualTo(participants.size());
    thenInStorage.correctDataIsPersisted(participantWithNewData);
  }

  @Test
  @WithMockUser
  public void shouldReturnUpdatedHistoricalRetreatsOfParticipant() throws Exception {
    whenInParticipantsApi.singleParticipantIsAdded(participantWithFullData);
    final Long participantId = whenInStorage.participantWithTheSameDataIsFound(participantWithFullData);
    final Set<Long> retreatsIds = whenInStorage.historicalRetreatsForParticipantAreFound(participantId);
    final Set<RetreatTurnValue> updatedRetreats = retreatsIds.stream()
        .map(id -> RetreatTurnValue.builder()
            .id(id)
            .stage("New stage " + id)
            .location("New location " + id)
            .year(1990)
            .build())
        .limit(1)
        .collect(Collectors.toSet());
    updatedRetreats.add(RetreatTurnValue.builder()
        .stage("ONŻ")
        .location("Brand new")
        .year(2000)
        .build());
    final ParticipantDTO updatedParticipant =
        ParticipantFactory.withSampleDataAndHistoricalRetreats(participantId, updatedRetreats);

    final ResultActions response = whenInParticipantsApi.singleParticipantIsUpdated(updatedParticipant);

    final Set<RetreatTurnValue> retreatsWithIds = thenInStorage.historicalRetreatsHaveIds(participantId);
    final ParticipantDTO expectedParticipant = ParticipantFactory.from(updatedParticipant)
        .withId(participantId)
        .withHistoricalRetreats(retreatsWithIds)
        .clone();
    thenInParticipantsApi.okResponseHasCorrectParticipantData(response, expectedParticipant);
  }

  @Test
  @WithMockUser
  public void shouldRemoveHistoricalRetreatFromParticipantOnUpdate() throws Exception {
    whenInParticipantsApi.singleParticipantIsAdded(participantWithFullData);
    final Long participantId = whenInStorage.participantWithTheSameDataIsFound(participantWithFullData);
    final Set<Long> retreatsIds = whenInStorage.historicalRetreatsForParticipantAreFound(participantId);
    final Set<RetreatTurnValue> updatedRetreats = retreatsIds.stream()
        .map(id -> RetreatTurnValue.builder()
            .id(id)
            .stage("New stage " + id)
            .location("New location " + id)
            .year(1990)
            .build())
        .limit(1)
        .collect(Collectors.toSet());
    final ParticipantDTO updatedParticipant =
        ParticipantFactory.withSampleDataAndHistoricalRetreats(participantId, updatedRetreats);

    final ResultActions response = whenInParticipantsApi.singleParticipantIsUpdated(updatedParticipant);

    thenInParticipantsApi.okResponseHasCorrectParticipantData(response, updatedParticipant);
  }

  @Test
  @WithMockUser
  public void shouldAddHistoricalRetreatToParticipantOnUpdate() throws Exception {
    whenInParticipantsApi.singleParticipantIsAdded(participantWithSampleData);
    final Long participantId = whenInStorage.participantWithTheSameDataIsFound(participantWithSampleData);
    Set<RetreatTurnValue> newRetreats = Sets.newHashSet(
        RetreatTurnValue.builder()
            .stage("ONŻ")
            .location("Brand new")
            .year(2000)
            .build(),
        RetreatTurnValue.builder()
            .stage("OND")
            .location("Another brand new")
            .year(2010)
            .build()
    );
    final ParticipantDTO updatedParticipant =
        ParticipantFactory.withSampleDataAndHistoricalRetreats(participantId, newRetreats);

    final ResultActions response = whenInParticipantsApi.singleParticipantIsUpdated(updatedParticipant);

    final Set<RetreatTurnValue> retreatsWithIds = thenInStorage.historicalRetreatsHaveIds(participantId);
    final ParticipantDTO expectedParticipant = ParticipantFactory.from(updatedParticipant)
        .withId(participantId)
        .withHistoricalRetreats(retreatsWithIds)
        .clone();
    thenInParticipantsApi.okResponseHasCorrectParticipantData(response, expectedParticipant);
  }
}

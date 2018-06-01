package pl.oaza.warszawa.dor.rekolekcje.api.participants;

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

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ParticipantsAcceptanceTest extends BaseIntegrationTest {

  private ParticipantsStorageBehaviour whenInStorage;
  private ParticipantsStorageExpectations thenInStorage;

  private ParticipantsApiBehaviour whenInParticipantsApi;
  private ParticipantsApiExpectations thenInParticipantsApi;

  private final ParticipantDTO participantWithMinimalData = ParticipantFactory.participantWithMinimalData(1L);
  private final ParticipantDTO participantWithSampleData = ParticipantFactory.sampleParticipant(2L);
  private final ParticipantDTO participantWithFullData = ParticipantFactory.participantWithAllData();

  private final List<ParticipantDTO> participants = Arrays.asList(participantWithMinimalData, participantWithSampleData);

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
        ParticipantFactory.copyWithDifferentId(participantWithFullData, id, retreatsWithIds);
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
        ParticipantFactory.copyWithDifferentId(participantWithFullData, id, retreatsWithIds);
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

    final ParticipantDTO sampleParticipant = ParticipantFactory.sample();
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

    final ParticipantDTO participantWithNewData = ParticipantFactory.withNewData(id);
    final ResultActions response = whenInParticipantsApi.singleParticipantIsUpdated(participantWithNewData);

    thenInParticipantsApi.okResponseHasCorrectParticipantData(response, participantWithNewData);
  }

  @Test
  @WithMockUser
  public void shouldUpdatePersistedDataWhenSingleParticipantIsUpdated() throws Exception {
    whenInStorage.existParticipantsWithSampleData(participants);
    final Long id = participantWithSampleData.getId();

    final ParticipantDTO participantWithNewData = ParticipantFactory.withNewData(id);
    whenInParticipantsApi.singleParticipantIsUpdated(participantWithNewData);

    thenInStorage.numberOfParticipantsIsEqualTo(participants.size());
    thenInStorage.correctDataIsPersisted(participantWithNewData);
  }
}

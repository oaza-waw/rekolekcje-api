package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.storage.ParticipantData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.storage.ParticipantsDatabase;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.storage.ParticipantsStorageBehaviour;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.storage.ParticipantsStorageExpectations;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantFactory;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantsApiBehaviour;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantsApiExpectations;

import java.util.Arrays;
import java.util.List;

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

  @WithMockUser
  @Test
  public void shouldUpdateSingleParticipant() throws Exception {
    ParticipantDTO existingParticipant = ParticipantFactory.sampleParticipant(3L);
    final long existingParticipantId = existingParticipant.getId();
    whenInStorage.existSomeParticipants(Lists.newArrayList(existingParticipant));
    ParticipantDTO participantWithNewData = ParticipantDTO.builder()
        .id(existingParticipantId)
        .firstName("Luke")
        .lastName("Skywalker")
        .parishId(1L)
        .pesel("80020354321")
        .build();
    final ResultActions response = whenInParticipantsApi.singleParticipantIsUpdated(participantWithNewData);

    final ParticipantData storedParticipant = thenInStorage.participantExistsWithCorrectData(participantWithNewData);
    thenInParticipantsApi.okResponseHasFullParticipantData(response, storedParticipant);
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
    // given participant with full data is persisted through api
    whenInParticipantsApi.singleParticipantIsAdded(participantWithFullData);
    final long id = whenInStorage.participantWithTheSameDataIsFound(participantWithFullData);

    // when api is called for this participant
    final ResultActions response =
        whenInParticipantsApi.singleParticipantIsRequested(id);

    // full data is returned
    final ParticipantDTO expectedParticipant =
        ParticipantFactory.copyWithDifferentId(participantWithFullData, id);
    thenInParticipantsApi.okResponseHasCorrectParticipantData(response, expectedParticipant);
  }

  @Test
  @WithMockUser
  public void shouldReturnCorrectDataWhenNewParticipantIsAdded() throws Exception {
    // given some participants are already in database
    whenInStorage.existParticipantsWithSampleData(participants);

    // when new participant is added
    final ResultActions response = whenInParticipantsApi.singleParticipantIsAdded(participantWithFullData);

    // then the same data is returned
    final long id = whenInStorage.participantWithTheSameDataIsFound(participantWithFullData);
    final ParticipantDTO expectedParticipant =
        ParticipantFactory.copyWithDifferentId(participantWithFullData, id);
    thenInParticipantsApi.createdResponseHasCorrectParticipantData(response, expectedParticipant);
  }

  @Test
  @WithMockUser
  public void shouldSetParticipantIdWhenANewOneIsAdded() throws Exception {
    // given some participants are already in database
    whenInStorage.existParticipantsWithSampleData(participants);

    // when participant without id is added
    final ResultActions response = whenInParticipantsApi.singleParticipantIsAdded(participantWithFullData);

    // then participant with id is returned
    thenInParticipantsApi.responseHasParticipantWithId(response);
  }

  @Test
  @WithMockUser
  public void shouldPersistParticipantWhenHeIsAdded() throws Exception {
    // given some participants are already in database
    whenInStorage.existParticipantsWithSampleData(participants);

    // when sample participant is added
    final ParticipantDTO sampleParticipant = ParticipantFactory.sample();
    whenInParticipantsApi.singleParticipantIsAdded(sampleParticipant);

    // then its data is persisted in database
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
    // given participant exists in system
    whenInStorage.existSingleParticipantWithSampleData(participantWithSampleData);
    final Long id = participantWithSampleData.getId();

    // when participant is deleted
    final ResultActions response =
        whenInParticipantsApi.singleParticipantIsDeleted(id);

    // then participant no longer exists in database
    thenInParticipantsApi.okStatusIsReturned(response);
    thenInStorage.participantNoLongerExists(id);
  }

  @Test
  @WithMockUser
  public void shouldReturnParticipantWithNewDataWhenSingleOneIsUpdated() throws Exception {
    // given some participants ale already in database
    // when participant with the same id and new data is sent
    // then participant with updated data is returned
  }

  @Test
  @WithMockUser
  public void shouldUpdatePersistedDataWhenSingleParticipantIsUpdated() throws Exception {
    // given some participants ale already in database
    // when participant with the same id and new data is sent
    // then persisted data of this participant is updated
  }
}

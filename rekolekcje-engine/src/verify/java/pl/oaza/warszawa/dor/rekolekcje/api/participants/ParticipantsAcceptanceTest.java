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

  private ParticipantsDatabase participantsDatabase;

  private ParticipantsStorageBehaviour whenInStorage;
  private ParticipantsStorageExpectations thenInStorage;

  private ParticipantsApiBehaviour whenInParticipantsApi;
  private ParticipantsApiExpectations thenInParticipantsApi;

  private final ParticipantDTO firstParticipant = ParticipantFactory.participantWithMinimalData(1L);
  private final ParticipantDTO secondParticipant = ParticipantFactory.participantWithAllData(2L);

  private final List<ParticipantDTO> participants = Arrays.asList(firstParticipant, secondParticipant);

  @Before
  public void setup() throws Exception {
    super.setup();

    participantsDatabase = new ParticipantsDatabase(jdbcTemplate);
    participantsDatabase.clearParticipants();

    whenInStorage = new ParticipantsStorageBehaviour(participantsDatabase);
    thenInStorage = new ParticipantsStorageExpectations(participantsDatabase);

    whenInParticipantsApi = new ParticipantsApiBehaviour(mockMvc, jsonMapper);
    thenInParticipantsApi = new ParticipantsApiExpectations(jsonMapper);

    participantsDatabase.clearParticipants();
  }

  @WithMockUser
  @Test
  public void shouldGetAllParticipants() throws Exception {
    whenInStorage.existSomeParticipants(participants);
    final ResultActions response = whenInParticipantsApi.allParticipantsAreRequested();
    thenInParticipantsApi.responseHasAllParticipants(response, participants);
  }

  @WithMockUser
  @Test
  public void shouldGetFullDataOfSingleParticipant() throws Exception {
    whenInStorage.existSomeParticipants(participants);
    final ParticipantData storedParticipant = participantsDatabase.getSavedParticipantData(secondParticipant);
    final ResultActions response = whenInParticipantsApi.singleParticipantIsRequested(storedParticipant.getId());
    thenInParticipantsApi.okResponseHasFullParticipantData(response, storedParticipant);
  }

  @WithMockUser
  @Test
  public void shouldAddSingleParticipant() throws Exception {
    whenInStorage.existSomeParticipants(participants);
    final ParticipantDTO participantToAdd = ParticipantFactory.sampleParticipant(null);
    final ResultActions response = whenInParticipantsApi.singleParticipantIsAdded(participantToAdd);
    thenInStorage.numberOfParticipantsIsEqualTo(participants.size() + 1);
    final ParticipantData storedParticipant = thenInStorage.participantExistsWithCorrectData(participantToAdd);
    thenInParticipantsApi.createdResponseHasFullParticipantData(response, storedParticipant);
  }

  @WithMockUser
  @Test
  public void shouldDeleteSingleParticipant() throws Exception {
    whenInStorage.existSomeParticipants(participants);
    final Long id = secondParticipant.getId();
    final ResultActions response = whenInParticipantsApi.singleParticipantIsDeleted(id);
    thenInParticipantsApi.okStatusIsReturned(response);
    thenInStorage.participantNoLongerExists(id);
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
}

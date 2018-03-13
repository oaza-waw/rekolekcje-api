package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantFactory;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantsRequestBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ParticipantsAcceptanceTest extends BaseIntegrationTest {

  private final ParticipantsRequestBuilder requestBuilder = new ParticipantsRequestBuilder();

  private final ParticipantDTO firstParticipant = ParticipantFactory.participantWithMinimalData(1L);
  private final ParticipantDTO secondParticipant = ParticipantFactory.participantWithAllData(2L);

  private final List<ParticipantDTO> participants = Arrays.asList(firstParticipant, secondParticipant);

  @Before
  public void setup() throws Exception {
    super.setup();

    database.saveParticipants(participants);
  }

  @After
  public void teardown() {
    database.clearParticipants();
  }

  @WithMockUser
  @Test
  public void shouldGetAllParticipants() throws Exception {
    // given
    final MockHttpServletRequestBuilder getAllRequest = requestBuilder.createGetAllRequest();

    // when
    final ResultActions response = mockMvc.perform(getAllRequest);

    // then
    final String expectedJsonContent = jsonMapper.writeValueAsString(participants);
    response.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expectedJsonContent));
  }

  @WithMockUser
  @Test
  public void shouldGetFullDataOfSingleParticipant() throws Exception {
    // given participant with full data is in the system
    final ParticipantData participantDataToFind = database.getSavedParticipantData(secondParticipant);
    final Long idToFind = participantDataToFind.getId();
    final MockHttpServletRequestBuilder getOneRequest =
        requestBuilder.createGetOneRequest(idToFind);

    // when requesting this participant
    final ResultActions response = mockMvc.perform(getOneRequest)
        .andExpect(status().isOk());

    // then all his data is retrieved
//    final String expectedJsonContent = jsonMapper.writeValueAsString(participantDataToFind);
    final String content = response.andReturn().getResponse().getContentAsString();
    final DocumentContext parsedJson = JsonPath.parse(content);
    assertThat(parsedJson.read("$.id", Long.class)).isEqualTo(participantDataToFind.getId());
    assertThat(parsedJson.read("$.pesel", Long.class)).isEqualTo(participantDataToFind.getPesel());
    assertThat(parsedJson.read("$.firstName", String.class)).isEqualTo(participantDataToFind.getFirstName());
    assertThat(parsedJson.read("$.lastName", String.class)).isEqualTo(participantDataToFind.getLastName());
  }

  @WithMockUser
  @Test
  public void shouldAddSingleParticipant() throws Exception {
    // given
    final ParticipantDTO participantToAdd = ParticipantFactory.sampleParticipant(null);
    final int numberOfParticipantsBefore = participants.size();

    // when
    final MockHttpServletRequestBuilder addOneRequest =
        requestBuilder.createAddOneRequest(participantToAdd);
    mockMvc.perform(addOneRequest);

    // then
    final List<ParticipantData> foundParticipantData = database.getAllParticipantData();
    assertThat(foundParticipantData).hasSize(numberOfParticipantsBefore + 1);

    final ParticipantData participantData = findOneInSystemWithTheSameNameAndPesel(participantToAdd);
    assertThat(participantData.getId()).isNotNull();
    assertThatDataIsTheSame(participantToAdd, participantData);
  }

  @WithMockUser
  @Test
  public void shouldReturnResponseWithParticipantWhenAddingOne() throws Exception {
    // given
    final ParticipantDTO participantToAdd = ParticipantFactory.sampleParticipant(null);

    // when
    final MockHttpServletRequestBuilder addOneRequest =
        requestBuilder.createAddOneRequest(participantToAdd);
    final ResultActions resultActions = mockMvc.perform(addOneRequest)
        .andExpect(status().isCreated());

    // then
    final ParticipantData participantInSystem = findOneInSystemWithTheSameNameAndPesel(participantToAdd);
    final String content = resultActions.andReturn().getResponse().getContentAsString();
    final DocumentContext parsedJson = JsonPath.parse(content);
    assertThat(parsedJson.read("$.id", Long.class)).isEqualTo(participantInSystem.getId());
    assertThat(parsedJson.read("$.firstName", String.class)).isEqualTo(participantInSystem.getFirstName());
    assertThat(parsedJson.read("$.lastName", String.class)).isEqualTo(participantInSystem.getLastName());
    assertThat(parsedJson.read("$.pesel", Long.class)).isEqualTo(participantInSystem.getPesel());
  }

  @WithMockUser
  @Test
  public void shouldDeleteSingleParticipant() throws Exception {
    // given
    final MockHttpServletRequestBuilder deleteOneRequest =
        requestBuilder.createDeleteRequest(secondParticipant.getId());

    // when
    mockMvc.perform(deleteOneRequest)
        .andExpect(status().isOk());

    // then
    final List<Long> idsInSystem = database.getAllParticipantData().stream()
        .map(ParticipantData::getId)
        .collect(toList());
    assertThat(idsInSystem).doesNotContain(secondParticipant.getId());
  }

  @WithMockUser
  @Test
  public void shouldUpdateSingleParticipant() throws Exception {
    // given
    ParticipantDTO existingParticipant = ParticipantFactory.sampleParticipant(3L);
    database.saveParticipants(Collections.singletonList(existingParticipant));
    final long existingParticipantId = existingParticipant.getId();
    ParticipantDTO participantWithNewData = ParticipantDTO.builder()
        .id(existingParticipantId)
        .firstName("Luke")
        .lastName("Skywalker")
        .address("Tatooine")
        .parishId(1L)
        .pesel(80020354321L)
        .build();
    final MockHttpServletRequestBuilder updateRequest =
        requestBuilder.createUpdateRequest(participantWithNewData);

    // when
    mockMvc.perform(updateRequest)
        .andExpect(status().isOk())
        .andExpect(content().json(jsonMapper.writeValueAsString(participantWithNewData)));

    // then
    final ParticipantData participantInSystem = database.getSavedParticipantData(existingParticipantId);
    assertThat(participantInSystem.getId()).isEqualTo(participantWithNewData.getId());
    assertThatDataIsTheSame(participantWithNewData, participantInSystem);
  }

  private void assertThatDataIsTheSame(ParticipantDTO dto, ParticipantData data) {
    assertThat(dto.getFirstName()).isEqualTo(data.getFirstName());
    assertThat(dto.getLastName()).isEqualTo(data.getLastName());
    assertThat(dto.getAddress()).isEqualTo(data.getAddress());
    assertThat(dto.getPesel()).isEqualTo(data.getPesel());
    assertThat(dto.getParishId()).isEqualTo(data.getParishId());
    assertThat(dto.getFatherName()).isEqualTo(data.getFatherName());
    assertThat(dto.getMotherName()).isEqualTo(data.getMotherName());
    assertThat(dto.getChristeningPlace()).isEqualTo(data.getChristeningPlace());
    assertThat(dto.getChristeningDate()).isEqualTo(data.getChristeningDate());
  }

  private ParticipantData findOneInSystemWithTheSameNameAndPesel(ParticipantDTO participant) {
    return database.getSavedParticipantData(participant);
  }
}

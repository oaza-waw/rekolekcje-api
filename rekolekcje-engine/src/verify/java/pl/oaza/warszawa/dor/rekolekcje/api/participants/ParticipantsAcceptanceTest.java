package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.ParticipantData;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantFactory;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantsRequestBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
  public void shouldGetSingleParticipant() throws Exception {
    // given
    final MockHttpServletRequestBuilder getOneRequest =
        requestBuilder.createGetOneRequest(firstParticipant.getId());

    // when
    final ResultActions response = mockMvc.perform(getOneRequest);

    // then
    final String expectedJsonContent = jsonMapper.writeValueAsString(firstParticipant);
    response.andExpect(status().isOk())
        .andExpect(content().json(expectedJsonContent));
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
    final MvcResult result = mockMvc.perform(addOneRequest)
        .andExpect(status().isCreated())
        .andReturn();

    // then
    final ParticipantData participantData = findOneInSystemWithTheSameNameAndPesel(participantToAdd);
    assertThat(result.getResponse().getContentAsString())
        .isEqualTo(jsonMapper.writeValueAsString(participantData));
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

  private void assertThatDataIsTheSame(ParticipantDTO participantToAdd, ParticipantData participantData) {
    assertThat(participantData.getFirstName()).isEqualTo(participantToAdd.getFirstName());
    assertThat(participantData.getLastName()).isEqualTo(participantToAdd.getLastName());
    assertThat(participantData.getAddress()).isEqualTo(participantToAdd.getAddress());
    assertThat(participantData.getPesel()).isEqualTo(participantToAdd.getPesel());
    assertThat(participantData.getParishId()).isEqualTo(participantToAdd.getParishId());
  }

  private ParticipantData findOneInSystemWithTheSameNameAndPesel(ParticipantDTO participant) {
    return database.getSavedParticipantData(participant.getFirstName(),
        participant.getLastName(),
        participant.getPesel());
  }
}

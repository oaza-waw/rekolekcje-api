package pl.oaza.warszawa.dor.rekolekcje.api.integration;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.ParticipantFactory;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ParticipantsAcceptanceTest extends ParticipantsIntegrationTest {

  private final String PARTICIPANTS_API_URI = "/api/participants";

  private Gson jsonMapper = new Gson();

  private final ParticipantDTO firstParticipant = ParticipantFactory.participantWithMinimalData();
  private final ParticipantDTO secondParticipant = ParticipantFactory.participantWithAllData();

  @Before
  public void setup() {
    super.setup();

    final List<ParticipantDTO> participantDTOs = Arrays.asList(firstParticipant, secondParticipant);
    saveManyToRepository(participantDTOs);
  }

  @After
  public void teardown() {
    clearRepository();
  }

  @Test
  public void shouldGetAllParticipants() throws Exception {
    // given
    final MockHttpServletRequestBuilder getAllRequest = get(PARTICIPANTS_API_URI);

    // when
    final ResultActions response = mockMvc.perform(getAllRequest);

    // then
    final String expectedJsonContent = jsonMapper.toJson(getAllParticipantsCurrentlyInSystem());
    response.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expectedJsonContent));
  }

  @Test
  public void shouldGetSingleParticipant() throws Exception {
    // given
    ParticipantDTO singleParticipant = findOneInSystemWithTheSameData(firstParticipant);
    long idOfFirstParticipant = singleParticipant.getId();

    // when
    final MockHttpServletRequestBuilder getOneRequest =
        createGetOneRequest(idOfFirstParticipant);
    final ResultActions response = mockMvc.perform(getOneRequest);

    // then
    final String expectedJsonContent = jsonMapper.toJson(singleParticipant);
    response.andExpect(status().isOk())
        .andExpect(content().json(expectedJsonContent));
  }

  @Test
  public void shouldAddSingleParticipant() throws Exception {
    // given
    final ParticipantDTO participantToAdd = ParticipantFactory.sampleParticipant();
    final int numberOfParticipantsBefore = getAllParticipantsCurrentlyInSystem().size();

    // when
    final MockHttpServletRequestBuilder addOneRequest =
        createAddOneRequest(participantToAdd);
    final ResultActions response = mockMvc.perform(addOneRequest);

    // then
    assertThat(getAllParticipantsCurrentlyInSystem()).hasSize(numberOfParticipantsBefore + 1);
    final ParticipantDTO addedParticipant = findOneInSystemWithTheSameData(participantToAdd);
    final ParticipantDTO participantWithId = participantToAdd.copyWithId(addedParticipant.getId());
    assertThat(getAllParticipantsCurrentlyInSystem()).contains(participantWithId);
  }

  @Test
  public void shouldReturnResponseWithParticipantWhenAddingOne() throws Exception {
    // given
    final ParticipantDTO participantToAdd = ParticipantFactory.sampleParticipant();

    // when
    final MockHttpServletRequestBuilder addOneRequest =
        createAddOneRequest(participantToAdd);
    final ResultActions response = mockMvc.perform(addOneRequest);

    // then
    final ParticipantDTO addedParticipant = findOneInSystemWithTheSameData(participantToAdd);
    assertThat(response.andReturn().getResponse().getContentAsString())
        .isEqualTo(jsonMapper.toJson(addedParticipant));
  }

  @Test
  public void shouldDeleteSingleParticipant() throws Exception {
    // given
    ParticipantDTO singleParticipant = findOneInSystemWithTheSameData(firstParticipant);
    long idOfParticipantToDelete = singleParticipant.getId();

    // when
    final MockHttpServletRequestBuilder deleteOneRequest =
        createDeleteRequest(idOfParticipantToDelete);
    final ResultActions response = mockMvc.perform(deleteOneRequest);

    // then
    response.andExpect(status().isOk());
    assertThat(getAllParticipantsCurrentlyInSystem()).doesNotContain(singleParticipant);
  }

  @Test
  public void shouldUpdateSingleParticipant() throws Exception {
    // given
    ParticipantDTO existingParticipant = ParticipantFactory.sampleParticipant();
    saveOneToRepository(existingParticipant);
    final long existingParticipantId = findOneInSystemWithTheSameData(existingParticipant).getId();
    ParticipantDTO participantWithNewData = ParticipantDTO.builder()
        .id(existingParticipantId)
        .firstName("Luke")
        .lastName("Skywalker")
        .address("Tatooine")
        .parishId(1L)
        .pesel(80020354321L)
        .build();

    // when
    final MockHttpServletRequestBuilder updateRequest = createUpdateRequest(participantWithNewData);
    final ResultActions response = mockMvc.perform(updateRequest);

    // then
    response.andExpect(status().isOk())
        .andExpect(content().json(jsonMapper.toJson(participantWithNewData)));
    final ParticipantDTO participantInSystem = findInSystem(existingParticipantId);
    assertThat(participantInSystem).isEqualTo(participantWithNewData);
  }

  private MockHttpServletRequestBuilder createGetOneRequest(long id) {
    return get(PARTICIPANTS_API_URI + "/" + id);
  }

  private MockHttpServletRequestBuilder createDeleteRequest(long idOfParticipantToDelete) {
    return delete(PARTICIPANTS_API_URI + "/" + idOfParticipantToDelete);
  }

  private MockHttpServletRequestBuilder createAddOneRequest(ParticipantDTO participantToAdd) {
    return post(PARTICIPANTS_API_URI)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.toJson(participantToAdd));
  }

  private MockHttpServletRequestBuilder createUpdateRequest(ParticipantDTO participantWithNewData) {
    return put(PARTICIPANTS_API_URI)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.toJson(participantWithNewData));
  }

  private ParticipantDTO findInSystem(long existingParticipantId) throws ParticipantNotFoundInSystemException {
    return getAllParticipantsCurrentlyInSystem().stream()
        .filter(p -> p.getId() == existingParticipantId)
        .findAny()
        .orElseThrow(ParticipantNotFoundInSystemException::new);
  }

  private ParticipantDTO findOneInSystemWithTheSameData(ParticipantDTO participant)
      throws ParticipantNotFoundInSystemException {
    return getAllParticipantsCurrentlyInSystem().stream()
        .filter(p -> Objects.equals(p.getFirstName(), participant.getFirstName()))
        .filter(p -> Objects.equals(p.getLastName(), participant.getLastName()))
        .filter(p -> p.getPesel() == participant.getPesel())
        .filter(p -> Objects.equals(p.getAddress(), participant.getAddress()))
        .filter(p -> Objects.equals(p.getParishId(), participant.getParishId()))
        .findAny()
        .orElseThrow(ParticipantNotFoundInSystemException::new);
  }

  private class ParticipantNotFoundInSystemException extends Exception {
  }
}

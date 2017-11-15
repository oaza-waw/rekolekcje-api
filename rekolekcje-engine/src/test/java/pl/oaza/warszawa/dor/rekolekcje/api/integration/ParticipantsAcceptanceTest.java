package pl.oaza.warszawa.dor.rekolekcje.api.integration;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParticipantsAcceptanceTest extends ParticipantsIntegrationTest {

  private final String PARTICIPANTS_API_URI = "/api/participants";

  private Gson jsonMapper = new Gson();

  private final ParticipantDTO firstParticipant = ParticipantDTO.builder("John", "Smith").build();
  private final ParticipantDTO secondParticipant = ParticipantDTO.builder("Jane", "Doe").build();

  @Before
  public void setup() {
    super.setup();
    saveManyToRepository(Arrays.asList(firstParticipant, secondParticipant));
  }

  @After
  public void teardown() {
    clearRepository();
  }

  @Test
  public void shouldReturnOKStatus() throws Exception {
    mockMvc
        .perform(get(PARTICIPANTS_API_URI + "/status"))
        .andExpect(status().isOk());
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
    ParticipantDTO singleParticipant = getAllParticipantsCurrentlyInSystem().stream()
        .filter(p -> p.getFirstName().equals(firstParticipant.getFirstName()))
        .findAny()
        .orElseThrow(ParticipantNotFoundInSystemException::new);
    long idOfFirstParticipant = singleParticipant.getId();
    final MockHttpServletRequestBuilder getOneRequest =
        get(PARTICIPANTS_API_URI + "/" + idOfFirstParticipant);

    // when
    final ResultActions response = mockMvc.perform(getOneRequest);

    // then
    final String expectedJsonContent = jsonMapper.toJson(singleParticipant);
    response.andExpect(status().isOk())
        .andExpect(content().json(expectedJsonContent));
  }

  //TODO: refactor, add case when there are already participants in system
  @Test
  public void shouldAddSingleParticipant() throws Exception {
    // given
    final ParticipantDTO participantToAdd =
        ParticipantDTO.builder("New", "Participant")
            .address("Address")
            .pesel(98101012345L)
            .parish("Parish")
            .build();
    final MockHttpServletRequestBuilder addOneRequest =
        post(PARTICIPANTS_API_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonMapper.toJson(participantToAdd));

    // when
    final ResultActions response = mockMvc.perform(addOneRequest);

    // then
    final List<ParticipantDTO> participantsInSystem = getAllParticipantsCurrentlyInSystem().stream()
        .filter(p -> Objects.equals(p.getLastName(), participantToAdd.getLastName())
            && (p.getPesel() == participantToAdd.getPesel()))
        .collect(Collectors.toList());
    final long participantId = participantsInSystem.stream()
        .findFirst()
        .map(ParticipantDTO::getId)
        .orElse(0L);
    assertThat(participantsInSystem).hasSize(1);
    final ParticipantDTO participantWithId =
        ParticipantDTO.builder(participantToAdd.getFirstName(), participantToAdd.getLastName())
            .id(participantId)
            .parish(participantToAdd.getParish())
            .address(participantToAdd.getAddress())
            .pesel(participantToAdd.getPesel())
            .build();
    assertThat(participantsInSystem).contains(participantWithId);
    assertThat(response.andReturn().getResponse().getContentAsString())
        .isEqualTo(jsonMapper.toJson(participantWithId));
  }

  @Test
  public void shouldDeleteSingleParticipant() throws Exception {
    // given
    ParticipantDTO singleParticipant = getAllParticipantsCurrentlyInSystem().stream()
        .filter(p -> p.getFirstName().equals(firstParticipant.getFirstName()))
        .findAny()
        .orElseThrow(ParticipantNotFoundInSystemException::new);
    long idOfParticipantToDelete = singleParticipant.getId();
    final MockHttpServletRequestBuilder deleteOneRequest =
        delete(PARTICIPANTS_API_URI + "/" + idOfParticipantToDelete);

    // when
    final ResultActions response = mockMvc.perform(deleteOneRequest);

    // then
    response.andExpect(status().isOk());
    assertThat(getAllParticipantsCurrentlyInSystem()).doesNotContain(singleParticipant);
  }

  @Test
  public void shouldUpdateSingleParticipant() throws Exception {
    // given
    ParticipantDTO existingParticipant = ParticipantDTO.builder("AAA", "BBB")
        .pesel(11111111111L)
        .address("CCCC")
        .parish("DDDDD")
        .build();
    saveOneToRepository(existingParticipant);
    final long existingParticipantId = getAllParticipantsCurrentlyInSystem().stream()
        .filter(p -> p.getPesel() == existingParticipant.getPesel())
        .findAny()
        .map(ParticipantDTO::getId)
        .orElse(0L);
    ParticipantDTO participantWithNewData = ParticipantDTO.builder("Luke", "Skywalker")
        .id(existingParticipantId)
        .address("Tatooine")
        .parish("None")
        .pesel(80020354321L)
        .build();

    // when
    final MockHttpServletRequestBuilder updateRequest =
        put(PARTICIPANTS_API_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonMapper.toJson(participantWithNewData));
    final ResultActions response = mockMvc.perform(updateRequest);

    // then
    response.andExpect(status().isOk())
        .andExpect(content().json(jsonMapper.toJson(participantWithNewData)));
    final ParticipantDTO participantInSystem = getAllParticipantsCurrentlyInSystem().stream()
        .filter(p -> p.getId() == existingParticipantId)
        .findAny()
        .orElseThrow(ParticipantNotFoundInSystemException::new);
    assertThat(participantInSystem).isEqualTo(participantWithNewData);
  }

  private class ParticipantNotFoundInSystemException extends Exception {}
}

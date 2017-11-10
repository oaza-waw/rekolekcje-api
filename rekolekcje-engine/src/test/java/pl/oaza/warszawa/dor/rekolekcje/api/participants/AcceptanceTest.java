package pl.oaza.warszawa.dor.rekolekcje.api.participants;

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
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.IntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcceptanceTest extends IntegrationTest {

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
    Optional<ParticipantDTO> singleParticipant = getAllParticipantsCurrentlyInSystem().stream()
        .filter(p -> p.getFirstName().equals(firstParticipant.getFirstName()))
        .findAny();
    long idOfFirstParticipant = singleParticipant
        .map(ParticipantDTO::getId)
        .orElseThrow(ParticipantNotFoundInSystemException::new);
    final MockHttpServletRequestBuilder getOneRequest =
        get(PARTICIPANTS_API_URI + "/" + idOfFirstParticipant);

    // when
    final ResultActions response = mockMvc.perform(getOneRequest);

    // then
    final String expectedJsonContent = jsonMapper
        .toJson(singleParticipant.orElseThrow(ParticipantNotFoundInSystemException::new));
    response.andExpect(status().isOk())
        .andExpect(content().json(expectedJsonContent));
  }

  @Test
  public void shouldAddSingleParticipant() throws Exception {
    // given
    // when
    // then
  }

  @Test
  public void shouldDeleteSingleParticipant() throws Exception {
    // given
    // when
    // then
  }

  @Test
  public void shouldUpdateSingleParticipant() throws Exception {
    // given
    // when
    // then
  }

  private class ParticipantNotFoundInSystemException extends Exception {}
}

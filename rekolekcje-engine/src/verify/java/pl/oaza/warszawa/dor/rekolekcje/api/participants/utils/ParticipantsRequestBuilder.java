package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

public class ParticipantsRequestBuilder {

  private final String PARTICIPANTS_API_URI = "/api/participants";

  private ObjectMapper jsonMapper = new ObjectMapper();

  public MockHttpServletRequestBuilder createGetAllRequest() {
    return get(PARTICIPANTS_API_URI);
  }

  public MockHttpServletRequestBuilder createGetOneRequest(long id) {
    return get(PARTICIPANTS_API_URI + "/" + id);
  }

  public MockHttpServletRequestBuilder createDeleteRequest(long idOfParticipantToDelete) {
    return delete(PARTICIPANTS_API_URI + "/" + idOfParticipantToDelete);
  }

  public MockHttpServletRequestBuilder createAddOneRequest(ParticipantDTO participantToAdd)
      throws JsonProcessingException {
    return post(PARTICIPANTS_API_URI)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.writeValueAsString(participantToAdd));
  }

  public MockHttpServletRequestBuilder createUpdateRequest(ParticipantDTO participantWithNewData)
      throws JsonProcessingException {
    return put(PARTICIPANTS_API_URI)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.writeValueAsString(participantWithNewData));
  }
}
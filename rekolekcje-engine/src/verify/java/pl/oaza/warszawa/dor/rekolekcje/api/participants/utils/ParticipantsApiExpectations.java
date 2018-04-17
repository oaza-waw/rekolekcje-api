package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ParticipantsApiExpectations {

    private final ObjectMapper jsonMapper;

    public ParticipantsApiExpectations(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public void responseHasAllParticipants(ResultActions response, List<ParticipantDTO> participants) throws Exception {
        final String expectedJsonContent = jsonMapper.writeValueAsString(participants);
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedJsonContent));
    }

    public void okStatusIsReturned(ResultActions response) throws Exception {
        response.andExpect(status().isOk());
    }

    public void okResponseHasFullParticipantData(ResultActions response, ParticipantData expectedParticipant) throws Exception {
        response.andExpect(status().isOk());
        responseHasFullParticipantData(response, expectedParticipant);
    }

    public void createdResponseHasFullParticipantData(ResultActions response, ParticipantData expectedParticipant) throws Exception {
        response.andExpect(status().isCreated());
        responseHasFullParticipantData(response, expectedParticipant);
    }

    private void responseHasFullParticipantData(ResultActions response, ParticipantData expectedParticipant) throws UnsupportedEncodingException {
        String content = response
                .andReturn()
                .getResponse()
                .getContentAsString();
        final DocumentContext parsedJson = JsonPath.parse(content);
        assertThat(parsedJson.read("$.id", Long.class)).isEqualTo(expectedParticipant.getId());
        assertThat(parsedJson.read("$.pesel", Long.class)).isEqualTo(expectedParticipant.getPesel());
        assertThat(parsedJson.read("$.firstName", String.class)).isEqualTo(expectedParticipant.getFirstName());
        assertThat(parsedJson.read("$.lastName", String.class)).isEqualTo(expectedParticipant.getLastName());
        //TODO: check all DTO fields
    }
}

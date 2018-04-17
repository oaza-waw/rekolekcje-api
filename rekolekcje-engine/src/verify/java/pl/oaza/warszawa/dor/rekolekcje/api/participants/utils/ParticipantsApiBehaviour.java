package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

public class ParticipantsApiBehaviour {

    private final MockMvc mockMvc;

    private final ParticipantsRequestBuilder requestBuilder = new ParticipantsRequestBuilder();

    public ParticipantsApiBehaviour(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions allParticipantsAreRequested() throws Exception {
        MockHttpServletRequestBuilder getAllRequest = requestBuilder.createGetAllRequest();
        return mockMvc.perform(getAllRequest);
    }

    public ResultActions singleParticipantIsRequested(long id) throws Exception {
        MockHttpServletRequestBuilder getOneRequest = requestBuilder.createGetOneRequest(id);
        return mockMvc.perform(getOneRequest);
    }

    public ResultActions singleParticipantIsAdded(ParticipantDTO participant) throws Exception {
        final MockHttpServletRequestBuilder addOneRequest = requestBuilder.createAddOneRequest(participant);
        return mockMvc.perform(addOneRequest);
    }

    public ResultActions singleParticipantIsDeleted(long id) throws Exception {
        final MockHttpServletRequestBuilder deleteRequest = requestBuilder.createDeleteRequest(id);
        return mockMvc.perform(deleteRequest);
    }

    public ResultActions singleParticipantIsUpdated(ParticipantDTO updatedParticipant) throws Exception {
        final MockHttpServletRequestBuilder updateRequest = requestBuilder.createUpdateRequest(updatedParticipant);
        return mockMvc.perform(updateRequest);
    }
}

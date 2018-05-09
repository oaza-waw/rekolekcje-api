package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gag.annotation.remark.Facepalm;
import com.google.gag.annotation.remark.Hack;
import com.google.gag.annotation.remark.WTF;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.storage.ParticipantData;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
    assertThat(parsedJson.read("$.parishId", Long.class)).isEqualTo(expectedParticipant.getParishId());
    verifyPersonalDataInResponse(parsedJson, expectedParticipant);
    verifyAddressValueInResponse(parsedJson, expectedParticipant);
    verifyExperienceValueInResponse(parsedJson, expectedParticipant);
  }

  private void verifyPersonalDataInResponse(DocumentContext parsedJson, ParticipantData expectedParticipant) {
    assertThat(parsedJson.read("$.personalData.motherName", String.class)).isEqualTo(expectedParticipant.getMotherName());
    assertThat(parsedJson.read("$.personalData.fatherName", String.class)).isEqualTo(expectedParticipant.getFatherName());
    assertThat(parsedJson.read("$.personalData.christeningPlace", String.class)).isEqualTo(expectedParticipant.getChristeningPlace());
    compareDates(parsedJson.read("$.personalData.christeningDate", String.class), expectedParticipant.getChristeningDate());
    assertThat(parsedJson.read("$.personalData.emergencyContactName", String.class)).isEqualTo(expectedParticipant.getCloseRelativeName());
    assertThat(parsedJson.read("$.personalData.emergencyContactNumber", Long.class)).isEqualTo(expectedParticipant.getCloseRelativeNumber());
  }

  private void verifyAddressValueInResponse(DocumentContext parsedJson, ParticipantData expectedParticipant) {
    assertThat(parsedJson.read("$.address.streetName", String.class)).isEqualTo(expectedParticipant.getStreet());
    assertThat(parsedJson.read("$.address.streetNumber", Integer.class)).isEqualTo(expectedParticipant.getStreetNumber());
    assertThat(parsedJson.read("$.address.flatNumber", Integer.class)).isEqualTo(expectedParticipant.getFlatNumber());
    assertThat(parsedJson.read("$.address.postalCode", String.class)).isEqualTo(expectedParticipant.getPostalCode());
    assertThat(parsedJson.read("$.address.city", String.class)).isEqualTo(expectedParticipant.getCity());
    assertThat(parsedJson.read("$.healthReport.currentTreatment", String.class)).isEqualTo(expectedParticipant.getCurrentTreatment());
  }

  private void verifyExperienceValueInResponse(DocumentContext parsedJson, ParticipantData expectedParticipant) {
    assertThat(parsedJson.read("$.experience.kwcStatus", String.class)).isEqualTo(expectedParticipant.getKwcStatus());
    compareDates(parsedJson.read("$.experience.kwcSince", String.class), expectedParticipant.getKwcSince());
    assertThat(parsedJson.read("$.experience.numberOfSummerRetreats", Integer.class)).isEqualTo(expectedParticipant.getNumberOfSummerRetreats());
    assertThat(parsedJson.read("$.experience.numberOfPrayerRetreats", Integer.class)).isEqualTo(expectedParticipant.getNumberOfPrayerRetreats());
  }

  @Hack("too tired to think...")
  @WTF("null, wuut?")
  @Facepalm("junior's code")
  private void compareDates(String dateFromJson, LocalDateTime storedDate) {
    if (dateFromJson == null && storedDate == null) {
      return;
    }

    final ZonedDateTime parsedDateFromJson = dateFromJson != null ? ZonedDateTime.parse(dateFromJson) : null;
    assertThat(parsedDateFromJson).isEqualTo(convertToUtcDateTime(storedDate));
  }

  private ZonedDateTime convertToUtcDateTime(LocalDateTime localDateTime) {
    return localDateTime == null ? null : ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
  }
}

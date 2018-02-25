package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.domain.ParishService;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ParishAcceptanceTest extends BaseIntegrationTest {

  private final ParishDTO firstParish = createParish(1L,"First", "Address no 1.");
  private final ParishDTO secondParish = createParish(2L,"Second", "Address no 2.");
  private final List<ParishDTO> parishes = Arrays.asList(firstParish, secondParish);

  @Autowired
  private ParishService parishService;

  @After
  public void tearDown() {
    database.clearParish();
  }

  @WithMockUser
  @Test
  public void shouldGetAllParishes() throws Exception {
    // given system has some parishes
    saveAllParishes(parishes);
    final MockHttpServletRequestBuilder getAllRequest = get("/api/parish");

    // when requesting all parishes
    final ResultActions response = mockMvc.perform(getAllRequest);

    // all are returned
    final List<ParishData> allParishData = database.getAllParishData();
    final String expectedJsonContent = jsonMapper.writeValueAsString(allParishData);
    response.andExpect(status().isOk())
        .andExpect(content().json(expectedJsonContent));
  }

  @WithMockUser
  @Test
  public void shouldGetSingleParish() throws Exception {
    // given system has some parishes
    saveAllParishes(parishes);
    final ParishData parishDataToFind = database.getSavedParishData(secondParish.getName());
    final Long idToFind = parishDataToFind.getId();
    final MockHttpServletRequestBuilder getOneRequest = get("/api/parish/" + idToFind);

    // when requesting single parish
    final ResultActions response = mockMvc.perform(getOneRequest);

    // it's details are returned
    final String expectedJsonContent = jsonMapper.writeValueAsString(parishDataToFind);
    response.andExpect(status().isOk())
        .andExpect(content().json(expectedJsonContent));
  }

  @WithMockUser
  @Test
  public void shouldCreateNewParish() throws Exception {
    // given new parish
    final ParishDTO parishToAdd = createParish(null, "Parish to add", "New parish address");

    // when posting new parish
    final MockHttpServletRequestBuilder addOneRequest = post("/api/parish")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.writeValueAsString(parishToAdd));
    final ResultActions response = mockMvc.perform(addOneRequest);

    // new parish is created
    response.andExpect(status().isOk());
    final ParishData parishData = database.getSavedParishData(parishToAdd.getName());
    assertThat(parishData.getName()).isEqualTo(parishToAdd.getName());
    assertThat(parishData.getAddress()).isEqualTo(parishToAdd.getAddress());
  }

  @WithMockUser
  @Test
  public void shouldAssignIdToParishOnCreation() throws Exception {
    // given parish data without id
    final ParishDTO parishToAdd = ParishDTO.builder().name("Brand new parish").address("Some address").build();

    // when posting this data to endpoint
    final MockHttpServletRequestBuilder addOneRequest = post("/api/parish")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.writeValueAsString(parishToAdd));
    final ResultActions response = mockMvc.perform(addOneRequest);

    // new parish is created with generated id
    final MvcResult result = response.andExpect(status().isOk()).andReturn();
    final JsonNode parsedResponseBody = jsonMapper.readTree(result.getResponse().getContentAsString());
    assertThat(parsedResponseBody.get("id").asLong()).isNotZero();
    final ParishData parishData = database.getSavedParishData(parishToAdd.getName());
    assertThat(parishData.getId()).isNotNull();
  }

  @WithMockUser
  @Test
  public void shouldDeleteSingleParish() throws Exception {
    // given system has some parishes
    saveAllParishes(parishes);
    final Long idToDelete = database.getSavedParishData(secondParish.getName()).getId();
    final MockHttpServletRequestBuilder deleteOneRequest = delete("/api/parish/" + idToDelete);

    // when posting delete request with id
    mockMvc.perform(deleteOneRequest)
        .andExpect(status().isOk());

    // then single parish is deleted
    final List<ParishData> allParishData = database.getAllParishData();
    assertThat(allParishData).hasSize(parishes.size() - 1);
    final ParishData secondParishData = ParishData.builder()
        .id(secondParish.getId())
        .name(secondParish.getName())
        .address(secondParish.getAddress())
        .build();
    assertThat(allParishData).doesNotContain(secondParishData);
  }

  @WithMockUser
  @Test
  public void shouldUpdateParishWithNewData() throws Exception {
    // given system has some parishes
    saveAllParishes(parishes);
    final ParishData parishDataToUpdate = database.getSavedParishData(secondParish.getName());
    final ParishDTO parishWithNewData = ParishDTO.builder()
        .id(parishDataToUpdate.getId())
        .name("Brand new name")
        .address("Completely new address")
        .build();

    // when posting update request with new data
    final MockHttpServletRequestBuilder updateOneRequest = put("/api/parish")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.writeValueAsString(parishWithNewData));
    final String expectedContent = jsonMapper.writeValueAsString(parishWithNewData);
    mockMvc.perform(updateOneRequest)
        .andExpect(status().isOk())
        .andExpect(content().json(expectedContent));

    // then single parish is updated
    final List<ParishData> allParishData = database.getAllParishData();
    assertThat(allParishData).hasSameSizeAs(parishes);
    final ParishData updatedParishData = database.getSavedParishData(parishWithNewData.getId());
    assertThat(updatedParishData.getName()).isEqualTo(parishWithNewData.getName());
    assertThat(updatedParishData.getAddress()).isEqualTo(parishWithNewData.getAddress());
  }

  private ParishDTO createParish(Long id, String name, String address) {
    return ParishDTO.builder()
        .id(id)
        .name(name)
        .address(address)
        .build();
  }

  private void saveAllParishes(List<ParishDTO> parishes) {
    parishes.forEach(parishDTO -> parishService.save(parishDTO));
  }
}

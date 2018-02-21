package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.ParishData;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.domain.ParishService;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ParishAcceptanceTest extends BaseIntegrationTest {

  private ParishDTO firstParish = createParish(1L,"First", "Address no 1.");
  private ParishDTO secondParish = createParish(2L,"Second", "Address no 2.");

  @Autowired
  private ParishService parishService;

  @WithMockUser
  @Test
  public void shouldGetAllParishes() throws Exception {
    // given system has some parishes
    final List<ParishDTO> parishes = Arrays.asList(firstParish, secondParish);
    saveAllParishes(parishes);
    final MockHttpServletRequestBuilder getAllRequest = get("/api/parish");

    // when requesting all parishes
    final ResultActions response = mockMvc.perform(getAllRequest);

    // all are returned
    final String expectedJsonContent = jsonMapper.writeValueAsString(parishes);
    response.andExpect(status().isOk())
        .andExpect(content().json(expectedJsonContent));
  }

  @WithMockUser
  @Test
  public void shouldGetSingleParish() throws Exception {
    // given system has some parishes
    final List<ParishDTO> parishes = Arrays.asList(firstParish, secondParish);
    saveAllParishes(parishes);
    final MockHttpServletRequestBuilder getOneRequest = get("/api/parish/" + secondParish.getId());

    // when requesting single parish
    final ResultActions response = mockMvc.perform(getOneRequest);

    // it's details are returned
    final String expectedJsonContent = jsonMapper.writeValueAsString(secondParish);
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

  private ParishDTO createParish(Long id, String name, String address) {
    return ParishDTO.builder()
        .id(id)
        .name(name)
        .address(address)
        .build();
  }

  private void saveAllParishes(List<ParishDTO> parishes) {
    parishes.forEach(parishDTO -> parishService.add(parishDTO));
  }
}

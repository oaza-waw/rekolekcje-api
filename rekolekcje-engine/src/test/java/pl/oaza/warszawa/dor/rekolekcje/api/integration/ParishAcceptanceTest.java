package pl.oaza.warszawa.dor.rekolekcje.api.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ParishAcceptanceTest {

  private ParishDTO firstParish = createParish(1L,"First", "Address no 1.");
  private ParishDTO secondParish = createParish(2L,"Second", "Address no 2.");

  private ParishDTO createParish(long id, String name, String address) {
    return ParishDTO.builder()
        .id(id)
        .name(name)
        .address(address)
        .build();
  }

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Autowired
  private ParishService parishService;

  private ObjectMapper jsonMapper = new ObjectMapper();

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .build();
  }

  private void saveAllParishes(List<ParishDTO> parishes) {
    parishes.forEach(parishDTO -> parishService.add(parishDTO));
  }

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

  @Test
  public void shouldCreateNewParish() throws Exception {
    // given new parish
    final ParishDTO parishToAdd = createParish(123, "Parish to add", "New parish address");

    // when posting new parish
    final MockHttpServletRequestBuilder addOneRequest = post("/api/parish")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.writeValueAsString(parishToAdd));
    final ResultActions response = mockMvc.perform(addOneRequest);

    // new parish is created
    response.andExpect(status().isOk());
    final List<ParishDTO> allParishes = parishService.findAll();
    assertThat(allParishes).contains(parishToAdd);
  }
}

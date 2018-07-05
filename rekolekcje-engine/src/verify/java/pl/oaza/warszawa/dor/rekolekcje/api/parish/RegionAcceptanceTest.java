package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.domain.RegionsService;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.RegionDTO;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegionAcceptanceTest extends BaseIntegrationTest {

  private final RegionDTO region1 = new RegionDTO(1L, "Kryta");
  private final RegionDTO region2= new RegionDTO(2L, "Maguuma Jungle");
  private final List<RegionDTO> allRegions = Arrays.asList(region1, region2);

  @Autowired
  private RegionsService service;

  @Before
  public void tearDown() {
    database.clearRegion();
  }

  @WithMockUser
  @Test
  public void shouldGetAllRegions() throws Exception {
    // given system has some regions
    allRegions.forEach(r -> service.save(r));
    final MockHttpServletRequestBuilder getAllRequest = get("/api/parish/regions");

    // when requesting all regions
    final ResultActions response = mockMvc.perform(getAllRequest);

    // all are returned
    final List<RegionData> expectedResult = database.getAllRegions();
    final String expectedJsonContent = jsonMapper.writeValueAsString(expectedResult);
    response.andExpect(status().isOk())
      .andExpect(content().json(expectedJsonContent));
  }

  @WithMockUser
  @Test
  public void shouldGetSingleRegion() throws Exception {
    // given system has some regions
    allRegions.forEach(r -> service.save(r));
    final RegionData dataToBeFound = database.getSavedRegionData(region1.getName());
    final Long idToFind = dataToBeFound.getId();
    final MockHttpServletRequestBuilder getOneRequest = get("/api/parish/regions/" + idToFind);

    // when requesting single region
    final ResultActions response = mockMvc.perform(getOneRequest);

    // it's details are returned
    final String expectedJsonContent = jsonMapper.writeValueAsString(dataToBeFound);
    response.andExpect(status().isOk())
            .andExpect(content().json(expectedJsonContent));
  }

  @WithMockUser
  @Test
  public void shouldCreateNewRegion() throws Exception {
    // given new region
    final RegionDTO newRegion = new RegionDTO(0L, "Domain of Vabbi");

    // when posting new region
    final MockHttpServletRequestBuilder addOneRequest = post("/api/parish/regions")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.writeValueAsString(newRegion));
    final ResultActions response = mockMvc.perform(addOneRequest);

    // new region is created
    response.andExpect(status().isOk());
    final RegionData data = database.getSavedRegionData(newRegion.getName());
    assertThat(data.getName()).isEqualTo(newRegion.getName());
  }

  @WithMockUser
  @Test
  public void shouldAssignIdToRegionOnCreation() throws Exception {
    // given region data without id
    final RegionDTO newRegion = new RegionDTO(null, "Domain of Ishtan");

    // when posting this data to endpoint
    final MockHttpServletRequestBuilder addOneRequest = post("/api/parish/regions")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.writeValueAsString(newRegion));
    final ResultActions response = mockMvc.perform(addOneRequest);

    // new region is created with generated id
    final MvcResult result = response.andExpect(status().isOk()).andReturn();
    final RegionData regionData = database.getSavedRegionData(newRegion.getName());
    assertThat(regionData.getId()).isNotNull();
    final JsonNode parsedResponseBody = jsonMapper.readTree(result.getResponse().getContentAsString());
    assertThat(parsedResponseBody.get("id").asLong()).isNotZero();
  }

  @WithMockUser
  @Test
  public void shouldDeleteSingleRegion() throws Exception {
    // given system has some regions
    allRegions.forEach(r -> service.save(r));
    RegionDTO regionToBeDeleted = region2;
    final Long idToDelete = database.getSavedRegionData(regionToBeDeleted.getName()).getId();
    final MockHttpServletRequestBuilder deleteOneRequest = delete("/api/parish/regions/" + idToDelete);

    // when posting delete request with id
    mockMvc.perform(deleteOneRequest)
        .andExpect(status().isOk());

    // then single region is deleted
    final List<RegionData> dbAllRegions = database.getAllRegions();
    assertThat(dbAllRegions).hasSize(this.allRegions.size() - 1);
    final RegionData secondRegionData = new RegionData(idToDelete, regionToBeDeleted.getName());
    assertThat(dbAllRegions).doesNotContain(secondRegionData);
  }

  @WithMockUser
  @Test
  public void shouldUpdateRegionWithNewData() throws Exception {
    // given system has some regions
    allRegions.forEach(r -> service.save(r));
    final RegionData regionDataToUpdate = database.getSavedRegionData(region2.getName());
    final RegionDTO updatedRegion =
        new RegionDTO(regionDataToUpdate.getId(), regionDataToUpdate.getName());

    // when posting update request with new data
    final MockHttpServletRequestBuilder updateOneRequest = put("/api/parish/regions")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(jsonMapper.writeValueAsString(updatedRegion));
    final String expectedContent = jsonMapper.writeValueAsString(updatedRegion);
    mockMvc.perform(updateOneRequest)
        .andExpect(status().isOk())
        .andExpect(content().json(expectedContent));

    // then single region is updated
    final List<RegionData> allRegionsData = database.getAllRegions();
    assertThat(allRegionsData).hasSameSizeAs(allRegions);
    final RegionData updatedRegionData = database.getSavedRegionData(updatedRegion.getId());
    assertThat(updatedRegionData.getName()).isEqualTo(updatedRegion.getName());
  }
}

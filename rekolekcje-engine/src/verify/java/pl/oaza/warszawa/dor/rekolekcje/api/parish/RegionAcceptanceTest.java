package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.oaza.warszawa.dor.rekolekcje.api.core.BaseIntegrationTest;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.domain.RegionsService;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.RegionDTO;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegionAcceptanceTest extends BaseIntegrationTest {

  private final RegionDTO region1 = new RegionDTO(1L, "Kryta");
  private final RegionDTO region2= new RegionDTO(2L, "Maguuma Jungle");
  private final List<RegionDTO> allRegions = Arrays.asList(region1, region2);

  @Autowired
  private RegionsService service;

  @After
  public void tearDown() {
    database.clearRegion();
  }

  @WithMockUser
  @Test
  public void shouldGetAllRegions() throws Exception {
    // given system has some parishes
    allRegions.forEach(r -> service.save(r));
    final MockHttpServletRequestBuilder getAllRequest = get("/api/parish/regions");

    // when requesting all parishes
    final ResultActions response = mockMvc.perform(getAllRequest);

    // all are returned
    final List<RegionData> result = database.getAllRegions();
    final String expectedJsonContent = jsonMapper.writeValueAsString(allRegions);
    response.andExpect(status().isOk())
      .andExpect(content().json(expectedJsonContent));
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;
import java.util.stream.Collectors;

@WebAppConfiguration
public class IntegrationTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private ParticipantsRepository repository;

  protected MockMvc mockMvc;

  @Before
  public void setup() {
    ConfigurableMockMvcBuilder mockMvcBuilder =
        MockMvcBuilders.webAppContextSetup(webApplicationContext);
    mockMvc = mockMvcBuilder.build();
  }

  public void saveOneToRepository(ParticipantDTO participantDTO) {
    repository.save(new Participant(participantDTO));
  }

  public void saveManyToRepository(List<ParticipantDTO> participantDTOs) {
    participantDTOs.forEach(participantDTO -> repository.save(new Participant(participantDTO)));
  }

  public List<ParticipantDTO> getAllParticipantsCurrentlyInSystem() {
    return repository.findAll().stream()
        .map(Participant::dto)
        .collect(Collectors.toList());
  }
}

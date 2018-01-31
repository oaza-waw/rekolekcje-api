package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.List;
import java.util.stream.Collectors;

@WebAppConfiguration
//@DataJpaTest
public abstract class ParticipantsIntegrationTest {

//  @Autowired
//  TestEntityManager entityManager;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private ParticipantsRepository participantsRepository;

  protected MockMvc mockMvc;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .build();
  }

  public void saveOneToRepository(ParticipantDTO participantDTO) {
    participantsRepository.save(new Participant(participantDTO));
  }

  protected void saveManyToRepository(List<ParticipantDTO> participantDTOs) {
    participantDTOs.forEach(participantDTO -> participantsRepository.save(new Participant(participantDTO)));
  }

  protected List<ParticipantDTO> getAllParticipantsCurrentlyInSystem() {
    return participantsRepository.findAll().stream()
        .map(Participant::dto)
        .collect(Collectors.toList());
  }

  protected void clearRepository() {
    participantsRepository.deleteAll();
  }
}

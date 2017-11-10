package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FindTest extends ParticipantsTest {

  @Test
  public void shouldFindAllParticipantsInRepository() {
    // given
    final ParticipantDTO firstParticipant = ParticipantDTO.builder("Will", "Smith").build();
    final ParticipantDTO secondParticipant = ParticipantDTO.builder("Nicolas", "Cage").build();
    List<ParticipantDTO> savedParticipants = saveAll(Arrays.asList(firstParticipant, secondParticipant));

    // when
    List<ParticipantDTO> foundParticipants = service.findAll();

    //then
    assertThat(foundParticipants).as("Found participants")
        .hasSize(savedParticipants.size());
  }
}

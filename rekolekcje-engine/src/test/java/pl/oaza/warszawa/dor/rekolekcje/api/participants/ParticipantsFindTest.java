package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsFindTest extends ParticipantsTest {

  private final ParticipantDTO firstParticipant = ParticipantDTO.builder("Will", "Smith").build();
  private final ParticipantDTO secondParticipant = ParticipantDTO.builder("Nicolas", "Cage").build();

  @Test
  public void shouldFindAllParticipantsInRepository() {
    // given
    List<ParticipantDTO> savedParticipants = saveAll(Arrays.asList(firstParticipant, secondParticipant));

    // when
    List<ParticipantDTO> foundParticipants = service.findAll();

    //then
    assertThat(foundParticipants).as("Found participants")
        .hasSize(savedParticipants.size())
        .containsAll(savedParticipants);
  }

  @Test
  public void shouldReturnEmptyListWhenNoParticipantsFound() {
    // when
    List<ParticipantDTO> foundParticipants = service.findAll();

    //then
    assertThat(foundParticipants).as("Found participants")
        .isEmpty();
  }

  @Test
  public void shouldFindOneParticipantInRepostitory() throws Exception {
    // given
    List<ParticipantDTO> savedParticipants = saveAll(Arrays.asList(firstParticipant, secondParticipant));
    ParticipantDTO expectedParticipant = savedParticipants.stream()
        .findFirst()
        .orElseThrow(Exception::new);
    long participantId = expectedParticipant.getId();

    // when
    ParticipantDTO foundParticipant = service.find(participantId);

    // then
    assertThat(foundParticipant).isEqualTo(expectedParticipant);
  }

  @Test
  public void shouldReturnNullWhenNoParticipantFound() {
    // given
    long idOfNotExistingParticipant = 0L;

    // when
    ParticipantDTO foundParticipant = service.find(idOfNotExistingParticipant);

    // then
    assertThat(foundParticipant).isNull();
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ParticipantsFindTest extends ParticipantsTest {

  private final ParticipantDTO firstParticipant = ParticipantDTO.builder().firstName("Will").lastName("Smith").build();
  private final ParticipantDTO secondParticipant = ParticipantDTO.builder().firstName("Nicolas").lastName("Cage").build();

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
  public void shouldFindOneParticipantInRepostitory() {
    // given
    saveAll(Arrays.asList(firstParticipant, secondParticipant));
    ParticipantDTO expectedParticipant = getAllInSystem().stream()
        .findFirst()
        .orElseThrow(() -> new ParticipantNotFoundException(0));
    long participantId = expectedParticipant.getId();

    // when
    ParticipantDTO foundParticipant = service.find(participantId);

    // then
    assertThat(foundParticipant).isEqualTo(expectedParticipant);
  }

  @Test
  public void shouldThrowExceptionWhenNoParticipantFound() {
    // given
    long idOfNotExistingParticipant = 0L;

    // when & then
    assertThatExceptionOfType(ParticipantNotFoundException.class)
        .isThrownBy(() -> service.find(idOfNotExistingParticipant))
        .withMessageContaining("id " + idOfNotExistingParticipant);
  }
}

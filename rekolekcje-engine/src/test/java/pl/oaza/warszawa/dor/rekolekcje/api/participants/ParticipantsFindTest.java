package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantNotFoundException;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantFactory;

public class ParticipantsFindTest extends ParticipantsTest {

  private final ParticipantDTO firstParticipant =
      ParticipantFactory.withSampleData("Kevin", "Garnett", "82020354321");

  private final ParticipantDTO secondParticipant =
      ParticipantFactory.withSampleData("Ray", "Allen", "82020312345");

  @Test
  public void shouldFindAllParticipantsInRepository() {
    // given
    List<ParticipantDTO> savedParticipants = saveAll(ImmutableList.of(firstParticipant, secondParticipant));

    // when
    List<ParticipantDTO> foundParticipants = facade.findAll();

    //then
    assertThat(foundParticipants).as("Found participants")
        .hasSize(savedParticipants.size())
        .containsAll(savedParticipants);
  }

  @Test
  public void shouldReturnEmptyListWhenNoParticipantsFound() {
    // when
    List<ParticipantDTO> foundParticipants = facade.findAll();

    //then
    assertThat(foundParticipants).as("Found participants")
        .isEmpty();
  }

  @Test
  public void shouldFindSingleParticipantWithAllDataFilled() {
    // given
    ParticipantDTO participantWithFullData = ParticipantFactory.withFullData(null);
    saveAll(ImmutableList.of(firstParticipant, secondParticipant, participantWithFullData));
    ParticipantDTO expectedParticipant =
        getCorrespondingParticipantFromSystem(participantWithFullData);
    long participantId = expectedParticipant.getId();

    // when
    ParticipantDTO foundParticipant = facade.find(participantId);

    // then
    assertThat(foundParticipant).isEqualTo(expectedParticipant);
  }

  @Test
  public void shouldThrowExceptionWhenNoParticipantFound() {
    // given
    long idOfNotExistingParticipant = 0L;

    // when & then
    assertThatExceptionOfType(ParticipantNotFoundException.class)
        .isThrownBy(() -> facade.find(idOfNotExistingParticipant))
        .withMessageContaining("id " + idOfNotExistingParticipant);
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateParticipantTest extends ParticipantsTest {

  private ParticipantDTO sampleParticipant1 =
      ParticipantDTO.builder("Paul", "George")
          .pesel(90010112345L)
          .address("Default City 23")
          .parish("Parish address 1")
          .build();
  private ParticipantDTO sampleParticipant2 =
      ParticipantDTO.builder("Roy", "Hibbert")
          .pesel(92010112345L)
          .address("Default City 42")
          .parish("Parish address 2")
          .build();
  private ParticipantDTO sampleParticipant3 =
      ParticipantDTO.builder("George", "Hill")
          .pesel(93010112345L)
          .address("Default City 11")
          .parish("Parish address 3")
          .build();

  @Test
  public void shouldUpdateExistingParticipant() {
    // given
    saveAll(Arrays.asList(sampleParticipant1, sampleParticipant2, sampleParticipant3));
    ParticipantDTO existingParticipantWithOldData = getAllInSystem().stream()
            .filter(p -> Objects.equals(p.getLastName(), sampleParticipant2.getLastName()))
            .findAny()
            .orElse(null);
    ParticipantDTO participantWithUpdatedData =
        ParticipantDTO.builder("Danny", "Granger")
            .id(existingParticipantWithOldData.getId())
            .pesel(95010112345L)
            .address("New City 123")
            .parish("New parish address 4")
            .build();

    // when
    ParticipantDTO participantAfterUpdate = service.update(participantWithUpdatedData);

    // then
    assertThat(participantAfterUpdate).isEqualTo(participantWithUpdatedData);
    ParticipantDTO participantInSystemWithTheSameId = getAllInSystem().stream()
        .filter(p -> p.getId() == participantWithUpdatedData.getId())
        .findAny()
        .orElse(null);
    assertThat(participantInSystemWithTheSameId).isEqualTo(participantWithUpdatedData);
  }
}

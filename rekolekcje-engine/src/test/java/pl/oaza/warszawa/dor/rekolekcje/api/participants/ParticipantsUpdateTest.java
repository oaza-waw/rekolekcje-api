package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsUpdateTest extends ParticipantsTest {

  private ParticipantDTO sampleParticipant1 =
      ParticipantDTO.builder()
          .firstName("Paul")
          .lastName("George")
          .pesel(90010112345L)
          .address("Default City 23")
          .parish(ParticipantFactory.sampleParish)
          .build();
  private ParticipantDTO sampleParticipant2 =
      ParticipantDTO.builder()
          .firstName("Roy")
          .lastName("Hibbert")
          .pesel(92010112345L)
          .address("Default City 42")
          .parish(ParticipantFactory.sampleParish)
          .build();
  private ParticipantDTO sampleParticipant3 =
      ParticipantDTO.builder()
          .firstName("George")
          .lastName("Hill")
          .pesel(93010112345L)
          .address("Default City 11")
          .parish(ParticipantFactory.sampleParish)
          .build();

  @Test
  public void shouldUpdateExistingParticipant() throws Exception {
    // given
    saveAll(Arrays.asList(sampleParticipant1, sampleParticipant2, sampleParticipant3));
    ParticipantDTO existingParticipantWithOldData = getAllInSystem().stream()
            .filter(p -> Objects.equals(p.getLastName(), sampleParticipant2.getLastName()))
            .findAny()
            .orElseThrow(ParticipantNotFoundException::new);
    ParticipantDTO participantWithUpdatedData =
        ParticipantDTO.builder()
            .id(existingParticipantWithOldData.getId())
            .firstName("Danny")
            .lastName("Granger")
            .pesel(95010112345L)
            .address("New City 123")
            .parish(ParticipantFactory.sampleParish)
            .build();

    // when
    ParticipantDTO participantAfterUpdate = service.update(participantWithUpdatedData);

    // then
    assertThat(participantAfterUpdate).isEqualTo(participantWithUpdatedData);
    ParticipantDTO participantInSystemWithTheSameId = getAllInSystem().stream()
        .filter(p -> p.getId() == participantWithUpdatedData.getId())
        .findAny()
        .orElseThrow(ParticipantNotFoundException::new);
    assertThat(participantInSystemWithTheSameId).isEqualTo(participantWithUpdatedData);
  }
}

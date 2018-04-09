package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsAddTest extends ParticipantsTest {

  @Test
  public void shouldAddSingleParticipantWithMinimalDataToEmptySystem() {
    // given
    ParticipantDTO participantDTO = ParticipantsTestData.participantWithMinimalData;

    // when
    ParticipantDTO addedParticipant = service.save(participantDTO);

    // then
    final List<ParticipantDTO> allInSystem = getAllInSystem();
    assertThat(allInSystem).hasSize(1);
    assertThat(allInSystem.get(0)).isEqualToIgnoringGivenFields(participantDTO, "id");
    assertThat(allInSystem.get(0)).isEqualTo(addedParticipant);
  }

  @Test
  public void shouldAssignIdWhenAddingNewParticipant() {
    // given
    ParticipantDTO participantDTO = ParticipantsTestData.participantWithMinimalData;

    // when
    ParticipantDTO addedParticipant = service.save(participantDTO);

    // then
    assertThat(addedParticipant.getId()).isNotNull().isNotZero();
    assertThat(getAllInSystem().get(0).getId()).isNotNull().isNotZero();
  }

  @Test
  public void shouldAddSingleParticipantWithFullDataToEmptySystem() {
    // given
    ParticipantDTO participantWithFullData = ParticipantsTestData.participantWithFullData;

    // when
    ParticipantDTO addedParticipant = service.save(participantWithFullData);

    // then
    assertThat(addedParticipant).isEqualToIgnoringGivenFields(participantWithFullData,"id");
    assertThat(getAllInSystem()).contains(addedParticipant);
  }

  @Test
  public void shouldAddParticipantToSystemWithExistingParticipants() {
    // given
    ParticipantDTO existingParticipant = ParticipantsTestData.sampleParticipant1;
    saveAll(Collections.singletonList(existingParticipant));

    // when
    ParticipantDTO addedParticipant = service.save(ParticipantsTestData.sampleParticipant2);

    // then
    final List<ParticipantDTO> allInSystem = getAllInSystem();
    assertThat(allInSystem).hasSize(2);
    assertThat(allInSystem).contains(addedParticipant);
  }

  //TODO: test for various time zones, i.e. when DTO has date with TZ different than UTC, it should be converted to UTC before saving.
  // Also, service should always return dates in UTC TZ.
}

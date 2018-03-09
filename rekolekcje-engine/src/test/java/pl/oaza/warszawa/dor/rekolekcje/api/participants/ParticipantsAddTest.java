package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParentsDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsAddTest extends ParticipantsTest {

  @Test
  public void shouldAddSingleParticipantWithMinimalDataToEmptySystem() {
    // given
    ParticipantDTO participantDTO = ParticipantDTO.builder()
        .firstName("Jack")
        .lastName("Frost")
        .build();

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
    ParticipantDTO participantDTO = ParticipantDTO.builder()
        .firstName("Jack")
        .lastName("Frost")
        .build();

    // when
    ParticipantDTO addedParticipant = service.save(participantDTO);

    // then
    assertThat(addedParticipant.getId()).isNotNull().isNotZero();
    assertThat(getAllInSystem().get(0).getId()).isNotNull().isNotZero();
  }

  @Test
  public void shouldAddSingleParticipantWithFullDataToEmptySystem() {
    // given
    ParentsDTO parents = ParentsDTO.builder()
        .motherName("Mary")
        .fatherName("Jake")
        .build();
    ParticipantDTO participantWithFullData = ParticipantDTO.builder()
        .firstName("Paul")
        .lastName("Pierce")
        .pesel(987654L)
        .address("Boston")
        .parishId(1L)
        .parents(parents)
        .build();

    // when
    ParticipantDTO addedParticipant = service.save(participantWithFullData);

    // then
    assertThat(addedParticipant).isEqualToIgnoringGivenFields(participantWithFullData,"id");
    assertThat(getAllInSystem()).contains(addedParticipant);
  }

  @Test
  public void shouldAddParticipantToSystemWithExistingParticipants() {
    // given
    ParticipantDTO existingParticipant = ParticipantDTO.builder()
        .firstName("Sample")
        .lastName("Participant")
        .build();
    saveAll(Collections.singletonList(existingParticipant));
    ParticipantDTO participantToAdd = ParticipantDTO.builder()
        .firstName("Jack")
        .lastName("Frost")
        .build();

    // when
    ParticipantDTO addedParticipant = service.save(participantToAdd);

    // then
    final List<ParticipantDTO> allInSystem = getAllInSystem();
    assertThat(allInSystem).hasSize(2);
    assertThat(allInSystem).contains(addedParticipant);
  }
}

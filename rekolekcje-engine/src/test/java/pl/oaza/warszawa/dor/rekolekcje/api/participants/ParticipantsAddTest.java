package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    final ParticipantDTO storedParticipant = allInSystem.get(0);
    assertThat(storedParticipant).isEqualToIgnoringGivenFields(participantDTO, "id");
    assertThat(storedParticipant).isEqualTo(addedParticipant);
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

  @Test
  public void shouldConvertDateToUtcTimezone() {
    // given participant with date in GMT+1 timezone
    ParticipantDTO participant = createParticipantWithDateInParisTimezone();

    // when participant is saved
    service.save(participant);

    // then stored participant has date in UTC
    final List<ParticipantDTO> allInSystem = getAllInSystem();
    final ParticipantDTO storedParticipant = allInSystem.get(0);
    final ZonedDateTime dateInLondon = dateInUTC();
    assertThat(storedParticipant.getPersonalData().getChristeningDate()).isEqualTo(dateInLondon);
  }

  @Test
  public void shouldAlwaysReturnDateInUtcTimezone() {
    // given participant with date in GMT+1 timezone
    ParticipantDTO participant = createParticipantWithDateInParisTimezone();

    // when participant is saved
    final ParticipantDTO returnedParticipant = service.save(participant);

    // then returned participant has date in UTC
    final ZonedDateTime dateInLondon = dateInUTC();
    assertThat(returnedParticipant.getPersonalData().getChristeningDate()).isEqualTo(dateInLondon);
  }

  private ParticipantDTO createParticipantWithDateInParisTimezone() {
    final ZonedDateTime dateInParis = dateInCET();
    PersonalDataValue personalData = PersonalDataValue.builder()
        .firstName("Sample")
        .lastName("Participant")
        .pesel("80030212221")
        .christeningDate(dateInParis)
        .build();
    return ParticipantDTO.builder()
        .personalData(personalData)
        .build();
  }

  private ZonedDateTime dateInUTC() {
    return ZonedDateTime.of(LocalDate.of(1989, 12, 31), LocalTime.of(23, 0), ZoneId.of("UTC"));
  }

  private ZonedDateTime dateInCET() {
    return ZonedDateTime.of(LocalDate.of(1990, 1, 1), LocalTime.of(0, 0), ZoneId.of("UTC+1"));
  }
}

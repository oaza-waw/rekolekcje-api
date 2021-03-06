package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantsTest;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.utils.ParticipantFactory;

public class ParticipantsAddTest extends ParticipantsTest {

  @Test
  public void shouldAddSingleParticipantWithMinimalDataToEmptySystem() {
    // given
    ParticipantDTO participantDTO = ParticipantFactory.withMinimalData(null);

    // when
    ParticipantDTO addedParticipant = facade.save(participantDTO);

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
    ParticipantDTO participantDTO = ParticipantFactory.withMinimalData(null);

    // when
    ParticipantDTO addedParticipant = facade.save(participantDTO);

    // then
    assertThat(addedParticipant.getId()).isNotNull().isNotZero();
    assertThat(getAllInSystem().get(0).getId()).isNotNull().isNotZero();
  }

  @Test
  public void shouldAddSingleParticipantWithFullDataToEmptySystem() {
    // given
    ParticipantDTO participantWithFullData = ParticipantFactory.withFullData(null);

    // when
    ParticipantDTO addedParticipant = facade.save(participantWithFullData);

    // then
    assertThat(addedParticipant).isEqualToIgnoringGivenFields(participantWithFullData,"id");
    assertThat(getAllInSystem()).contains(addedParticipant);
  }

  @Test
  public void shouldAddParticipantToSystemWithExistingParticipants() {
    // given
    ParticipantDTO existingParticipant = ParticipantFactory.withSampleData(null);
    saveAll(Collections.singletonList(existingParticipant));

    // when
    final ParticipantDTO participantToSave = ParticipantFactory.withSampleData("Another", "Guy", "99010244556");
    ParticipantDTO addedParticipant = facade.save(participantToSave);

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
    facade.save(participant);

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
    final ParticipantDTO returnedParticipant = facade.save(participant);

    // then returned participant has date in UTC
    final ZonedDateTime dateInLondon = dateInUTC();
    assertThat(returnedParticipant.getPersonalData().getChristeningDate()).isEqualTo(dateInLondon);
  }

  private ParticipantDTO createParticipantWithDateInParisTimezone() {
    final ZonedDateTime dateInParis = dateInCET();
    return ParticipantFactory.withChristeningDate(dateInParis);
  }

  private ZonedDateTime dateInUTC() {
    return ZonedDateTime.of(LocalDate.of(1989, 12, 31), LocalTime.of(23, 0), ZoneId.of("UTC"));
  }

  private ZonedDateTime dateInCET() {
    return ZonedDateTime.of(LocalDate.of(1990, 1, 1), LocalTime.of(0, 0), ZoneId.of("UTC+1"));
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ChristeningDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParentsDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.time.LocalDate;

class ParticipantsTestData {

  public static final ParticipantDTO participantWithMinimalData = ParticipantDTO.builder()
      .firstName("Jack")
      .lastName("Frost")
      .pesel(93010100000L)
      .build();

  private static final ParentsDTO sampleParents = ParentsDTO.builder()
      .motherName("Mary")
      .fatherName("Jake")
      .build();

  private static final ChristeningDTO sampleChristening = ChristeningDTO.builder()
      .place("Los Angeles")
      .date(LocalDate.of(1980, 1, 2))
      .build();

  public static final ParticipantDTO participantWithFullData = ParticipantDTO.builder()
      .firstName("Paul")
      .lastName("Pierce")
      .pesel(987654L)
      .address("Boston")
      .parishId(1L)
      .parents(sampleParents)
      .christening(sampleChristening)
      .build();

  public static final ParticipantDTO sampleParticipant1 = ParticipantDTO.builder()
      .firstName("Kevin")
      .lastName("Garnett")
      .pesel(82020354321L)
      .address("Boston TD Garden")
      .build();

  public static final ParticipantDTO sampleParticipant2 = ParticipantDTO.builder()
      .firstName("Ray")
      .lastName("Allen")
      .pesel(82020312345L)
      .address("TD Garden")
      .parishId(2L)
      .build();
}

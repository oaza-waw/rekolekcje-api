package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ChristeningDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParentsDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.time.LocalDate;

public class ParticipantFactory {

  public static ParticipantDTO sampleParticipant(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("Sample")
        .lastName("Participant")
        .address("Address Street no. 123")
        .pesel(98101012345L)
        .parishId(1L)
        .build();
  }

  public static ParticipantDTO participantWithMinimalData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("Minimal")
        .lastName("Participant")
        .pesel(92042312345L)
        .parishId(1L)
        .build();
  }

  private static final ParentsDTO sampleParents = ParentsDTO.builder()
      .motherName("Mary")
      .fatherName("Jake")
      .build();

  private static final ChristeningDTO sampleChristening = ChristeningDTO.builder()
      .place("Los Angeles")
      .date(LocalDate.of(1980, 1, 2))
      .build();

  public static ParticipantDTO participantWithAllData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("John")
        .lastName("Smith")
        .address("Street no. 987, City")
        .pesel(90042312345L)
        .parishId(1L)
        .parents(sampleParents)
        .christening(sampleChristening)
        .build();
  }
}

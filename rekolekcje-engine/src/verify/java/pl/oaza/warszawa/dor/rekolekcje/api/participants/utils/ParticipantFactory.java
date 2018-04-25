package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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

  public static ParticipantDTO participantWithAllData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("John")
        .lastName("Smith")
        .address("Street no. 987, City")
        .pesel(90042312345L)
        .parishId(1L)
        .personalData(fullPersonalData())
        .build();
  }

  private static PersonalData fullPersonalData() {
    return PersonalData.builder()
        .motherName("Mary")
        .fatherName("Jake")
        .christeningPlace("Los Angeles")
        .christeningDate(ZonedDateTime.of(LocalDateTime.of(1981, 2, 13, 23, 0), ZoneId.of("UTC")))
        .closeRelativeName("Uncle Bob")
        .closeRelativeNumber(444555666L)
        .build();
  }
}

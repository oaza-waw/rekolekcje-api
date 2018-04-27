package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ParticipantFactory {

  private static AddressValue sampleAddress = AddressValue.builder()
      .city("Chicago")
      .street("Narrow")
      .number(44)
      .build();

  public static ParticipantDTO sampleParticipant(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .firstName("Sample")
        .lastName("Participant")
        .address(sampleAddress)
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
        .address(fullAddress())
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

  private static AddressValue fullAddress() {
    return AddressValue.builder()
        .street("Broadway")
        .number(987)
        .flat(13)
        .code("12-654")
        .city("New York")
        .build();
  }
}

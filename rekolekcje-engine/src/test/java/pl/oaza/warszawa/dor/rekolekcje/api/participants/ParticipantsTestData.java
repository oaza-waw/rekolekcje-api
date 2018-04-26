package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class ParticipantsTestData {

  private static final PersonalData samplePersonalData = PersonalData.builder()
      .motherName("Mary")
      .fatherName("Jake")
      .christeningPlace("Los Angeles")
      .christeningDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")))
      .closeRelativeName("Uncle Bob")
      .closeRelativeNumber(111222333L)
      .build();

  private static final AddressValue sampleAddress = AddressValue.builder()
      .city("New York")
      .code("01-234")
      .street("42nd")
      .number(23)
      .flat(125)
      .build();

  private static final AddressValue sampleAddress2 = AddressValue.builder()
      .city("Boston")
      .code("01-423")
      .street("TD Garden")
      .number(23)
      .flat(125)
      .build();

  static final ParticipantDTO participantWithMinimalData = ParticipantDTO.builder()
      .firstName("Jack")
      .lastName("Frost")
      .pesel(93010100000L)
      .build();

  static final ParticipantDTO participantWithFullData = ParticipantDTO.builder()
      .firstName("Paul")
      .lastName("Pierce")
      .pesel(987654L)
      .parishId(1L)
      .address(sampleAddress)
      .personalData(samplePersonalData)
      .build();

  static final ParticipantDTO sampleParticipant1 = ParticipantDTO.builder()
      .firstName("Kevin")
      .lastName("Garnett")
      .pesel(82020354321L)
      .address(sampleAddress)
      .build();

  static final ParticipantDTO sampleParticipant2 = ParticipantDTO.builder()
      .firstName("Ray")
      .lastName("Allen")
      .pesel(82020312345L)
      .address(sampleAddress2)
      .parishId(2L)
      .build();
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class PersonalDataValueFactory {

  private final AddressValue sampleAddress = AddressValue.builder()
      .postalCode("AB 321")
      .build();

  private final AddressValue fullAddress = AddressValue.builder()
      .streetName("Broadway")
      .streetNumber(987)
      .flatNumber(13)
      .postalCode("12-654")
      .city("New York")
      .build();

  private final AddressValue updatedAddress = AddressValue.builder()
      .streetName("TD Garden")
      .streetNumber(43)
      .flatNumber(1)
      .postalCode("32-112")
      .city("Boston")
      .build();

  private final PersonalDataValue.PersonalDataValueBuilder sampleBuilder =
      PersonalDataValue.builder()
          .firstName("Sample")
          .lastName("Participant")
          .pesel("98101012345")
          .parishId(1L)
          .christeningDate(ZonedDateTime.of(LocalDateTime.of(1991, 11, 21, 12, 0), ZoneId.of("UTC")))
          .birthDate("10.10.1998")
          .address(sampleAddress);

  PersonalDataValue withMinimalData() {
    return PersonalDataValue.builder()
        .firstName("Minimal")
        .lastName("Participant")
        .pesel("92042312345")
        .parishId(1L)
        .build();
  }

  PersonalDataValue withSampleData() {
    return sampleBuilder.build();
  }

  PersonalDataValue withSampleData(String firstName, String lastName, String pesel) {
    return sampleBuilder
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .build();
  }

  PersonalDataValue withChristeningDate(ZonedDateTime christeningDate) {
    return sampleBuilder
        .christeningDate(christeningDate)
        .build();
  }

  PersonalDataValue withBirthDate(PersonalDataValue original, String birthDate) {
    return PersonalDataValue.builder()
        .birthDate(birthDate)
        .firstName(original.getFirstName())
        .lastName(original.getLastName())
        .pesel(original.getPesel())
        .email(original.getEmail())
        .phoneNumber(original.getPhoneNumber())
        .address(original.getAddress())
        .parishId(original.getParishId())
        .motherName(original.getMotherName())
        .fatherName(original.getFatherName())
        .christeningPlace(original.getChristeningPlace())
        .christeningDate(original.getChristeningDate())
        .emergencyContactName(original.getEmergencyContactName())
        .emergencyContactNumber(original.getEmergencyContactNumber())
        .schoolYear(original.getSchoolYear())
        .nameDay(original.getNameDay())
        .communityName(original.getCommunityName())
        .build();
  }

  PersonalDataValue withAddress(AddressValue address) {
    return sampleBuilder
        .address(address)
        .build();
  }

  PersonalDataValue withFullData() {
    return PersonalDataValue.builder()
        .firstName("John")
        .lastName("Smith")
        .pesel("90042312345")
        .parishId(1L)
        .email("john.smith@mail.com")
        .phoneNumber(998877665L)
        .address(fullAddress)
        .motherName("Mary")
        .fatherName("Jake")
        .birthDate("23.04.1990")
        .christeningPlace("Los Angeles")
        .christeningDate(ZonedDateTime.of(LocalDateTime.of(1981, 2, 13, 23, 0), ZoneId.of("UTC")))
        .emergencyContactName("Uncle Bob")
        .emergencyContactNumber(444555666L)
        .schoolYear("1 LO")
        .nameDay("24.04")
        .communityName("St. Peter")
        .build();
  }

  PersonalDataValue withUpdatedData() {
    return PersonalDataValue.builder()
        .firstName("Updated name")
        .lastName("Updated surname")
        .pesel("89112233445")
        .parishId(2L)
        .address(updatedAddress)
        .motherName("Updated mother name")
        .fatherName("Updated father name")
        .birthDate("22.11.1989")
        .christeningPlace("Updated christening place")
        .christeningDate(ZonedDateTime.of(LocalDateTime.of(1989, 11, 29, 23, 0), ZoneId.of("UTC")))
        .emergencyContactName("Updated emergency contact name")
        .emergencyContactNumber(999888777L)
        .build();
  }
}

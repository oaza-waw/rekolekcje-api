package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthStatusValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Participant {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String firstName;
  private String lastName;
  private Long pesel;
  private Long parishId;

  @Embedded
  private Address address;
  private String motherName;
  private String fatherName;
  private String christeningPlace;
  private LocalDateTime christeningDate;
  private String closeRelativeName;
  private Long closeRelativeNumber;

  @Embedded
  private HealthStatus healthStatus;

  ParticipantDTO dto() {
    PersonalData personalData = getPersonalData();
    AddressValue addressValue = getAddressValue();
    HealthStatusValue healthStatusValue = getHealthStatusValue();
    return ParticipantDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .parishId(parishId)
        .address(addressValue)
        .personalData(personalData)
        .healthStatus(healthStatusValue)
        .build();
  }

  private PersonalData getPersonalData() {
    return PersonalData.builder()
        .fatherName(fatherName)
        .motherName(motherName)
        .christeningPlace(christeningPlace)
        .christeningDate(convertToUtc(christeningDate))
        .emergencyContactName(closeRelativeName)
        .emergencyContactNumber(closeRelativeNumber)
        .build();
  }

  private AddressValue getAddressValue() {
    if (address != null) {
      return AddressValue.builder()
          .city(address.getCity())
          .postalCode(address.getPostalCode())
          .flatNumber(address.getFlatNumber())
          .streetNumber(address.getStreetNumber())
          .streetName(address.getStreet())
          .build();
    } else {
      return AddressValue.builder().build();
    }
  }

  private HealthStatusValue getHealthStatusValue() {
    if (healthStatus == null) return HealthStatusValue.builder().build();

    return HealthStatusValue.builder()
        .currentTreatment(healthStatus.getCurrentTreatment())
        .medications(healthStatus.getMedications())
        .allergies(healthStatus.getAllergies())
        .other(healthStatus.getOther())
        .build();
  }

  private ZonedDateTime convertToUtc(LocalDateTime dateTime) {
    return dateTime != null ? ZonedDateTime.of(dateTime, ZoneId.of("UTC")) : null;
  }
}

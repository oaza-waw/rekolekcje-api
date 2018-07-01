package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
class PersonalData {

  private String firstName;
  private String lastName;
  private String pesel;
  private Long phoneNumber;
  private String email;
  private Long parishId;

  @Embedded
  private Address address;

  private String motherName;
  private String fatherName;
  private String christeningPlace;
  private LocalDateTime christeningDate;
  private String emergencyContactName;
  private Long emergencyContactNumber;
  private String schoolYear;
  private String nameDay;
  private String communityName;


  PersonalDataValue value() {
    return PersonalDataValue.builder()
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .phoneNumber(phoneNumber)
        .email(email)
        .parishId(parishId)
        .address(getAddressValue())
        .motherName(motherName)
        .fatherName(fatherName)
        .birthDate(PeselToBirthDateConverter.convertPeselToBirthDate(pesel))
        .christeningPlace(christeningPlace)
        .christeningDate(DateConverter.convertToUtc(christeningDate))
        .emergencyContactName(emergencyContactName)
        .emergencyContactNumber(emergencyContactNumber)
        .schoolYear(schoolYear)
        .nameDay(nameDay)
        .communityName(communityName)
        .build();
  }

  private AddressValue getAddressValue() {
    return address != null ? address.value() : AddressValue.builder().build();
  }


}

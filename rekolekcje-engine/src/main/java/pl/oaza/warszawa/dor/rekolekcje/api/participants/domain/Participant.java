package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.*;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.*;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.*;

import javax.persistence.*;
import java.time.*;

import static pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.DateConverter.*;
import static pl.oaza.warszawa.dor.rekolekcje.api.participants.pesetUtil.PeselBirthDateConverter.*;

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
  private String pesel;
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
  private HealthReport healthReport;

  @Embedded
  private Experience experience;

  ParticipantDTO dto() {
    final PersonalData personalData = getPersonalData();
    final AddressValue addressValue = getAddressValue();
    final ExperienceValue experienceValue = getExperienceValue();
    final HealthReportValue healthReportValue = getHealthStatusValue();
    return ParticipantDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .parishId(parishId)
        .address(addressValue)
        .personalData(personalData)
        .experience(experienceValue)
        .healthReport(healthReportValue)
        .build();
  }

  private PersonalData getPersonalData() {
    return PersonalData.builder()
        .fatherName(fatherName)
        .motherName(motherName)
        .birthDate(convertPeselToBirthDate(pesel))
        .christeningPlace(christeningPlace)
        .christeningDate(convertToUtc(christeningDate))
        .emergencyContactName(closeRelativeName)
        .emergencyContactNumber(closeRelativeNumber)
        .build();
  }

  private AddressValue getAddressValue() {
    return address != null ? address.value() : AddressValue.builder().build();
  }

  private HealthReportValue getHealthStatusValue() {
    return healthReport != null ? healthReport.value() : HealthReportValue.builder().build();
  }

  private ExperienceValue getExperienceValue() {
    return experience != null ? experience.value() : ExperienceValue.builder().build();
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.DateConverter.convertToUtc;
import static pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.PeselToBirthDateConverter.convertPeselToBirthDate;

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
    final PersonalDataValue personalData = getPersonalData();
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

  private PersonalDataValue getPersonalData() {
    return PersonalDataValue.builder()
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

  void connectHistoricalTurnsWithParticipant() {
    experience.connectParticipant(this);
  }
}

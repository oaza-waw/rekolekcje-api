package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.CurrentApplicationValue;
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

  @Embedded
  private HealthReport healthReport;

  @Embedded
  private Experience experience;

  @Embedded
  private CurrentApplication currentApplication;

  ParticipantDTO dto() {
    final PersonalDataValue personalData = getPersonalData();
    final ExperienceValue experienceValue = getExperienceValue();
    final HealthReportValue healthReportValue = getHealthStatusValue();
    final CurrentApplicationValue currentApplicationValue = getCurrentApplicationValue();
    return ParticipantDTO.builder()
        .id(id)
        .personalData(personalData)
        .experience(experienceValue)
        .healthReport(healthReportValue)
        .currentApplication(currentApplicationValue)
        .build();
  }

  private PersonalDataValue getPersonalData() {
    final AddressValue addressValue = getAddressValue();
    return PersonalDataValue.builder()
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .phoneNumber(phoneNumber)
        .email(email)
        .parishId(parishId)
        .address(addressValue)
        .fatherName(fatherName)
        .motherName(motherName)
        .birthDate(convertPeselToBirthDate(pesel))
        .christeningPlace(christeningPlace)
        .christeningDate(convertToUtc(christeningDate))
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

  private HealthReportValue getHealthStatusValue() {
    return healthReport != null ? healthReport.value() : HealthReportValue.builder().build();
  }

  private ExperienceValue getExperienceValue() {
    return experience != null ? experience.value() : ExperienceValue.builder().build();
  }

  void connectHistoricalTurnsWithParticipant() {
    experience.connectParticipant(this);
  }

  private CurrentApplicationValue getCurrentApplicationValue() {
    return currentApplication != null ? currentApplication.value() : CurrentApplicationValue.builder().build();
  }
}

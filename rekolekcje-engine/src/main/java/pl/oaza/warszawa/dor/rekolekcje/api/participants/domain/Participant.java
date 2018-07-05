package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.CurrentApplicationValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Participant {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Embedded
  private PersonalData personalData;

  @Embedded
  private HealthReport healthReport;

  @Embedded
  private Experience experience;

  @Embedded
  private CurrentApplication currentApplication;

  ParticipantDTO dto() {
    final PersonalDataValue personalData = getPersonalDataValue();
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

  private PersonalDataValue getPersonalDataValue() {
    return personalData != null ? personalData.value() : PersonalDataValue.builder().build();
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
    return currentApplication != null ? currentApplication.value()
        : CurrentApplicationValue.builder().build();
  }
}

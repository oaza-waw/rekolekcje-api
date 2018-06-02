package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
class HealthReport {
  private String currentTreatment;
  private String mentalDisorders;
  private String medications;
  private String allergies;
  private String medicalDiet;
  private Boolean mountainWalkingContraindications;
  private String seriousDiseasesAndOperations;
  private Boolean locomotiveDisease;
  private String other;

  HealthReportValue value() {
    return HealthReportValue.builder()
        .currentTreatment(currentTreatment)
        .mentalDisorders(mentalDisorders)
        .medications(medications)
        .allergies(allergies)
        .medicalDiet(medicalDiet)
        .mountainWalkingContraindications(mountainWalkingContraindications)
        .seriousDiseasesAndOperations(seriousDiseasesAndOperations)
        .locomotiveDisease(locomotiveDisease)
        .other(other)
        .build();
  }
}

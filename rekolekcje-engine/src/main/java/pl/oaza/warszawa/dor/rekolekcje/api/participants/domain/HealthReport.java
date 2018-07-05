package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;

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
  private Boolean canHike;
  private String illnessHistory;
  private Boolean hasMotionSickness;
  private String other;

  HealthReportValue value() {
    return HealthReportValue.builder()
        .currentTreatment(currentTreatment)
        .mentalDisorders(mentalDisorders)
        .medications(medications)
        .allergies(allergies)
        .medicalDiet(medicalDiet)
        .canHike(canHike)
        .illnessHistory(illnessHistory)
        .hasMotionSickness(hasMotionSickness)
        .other(other)
        .build();
  }
}

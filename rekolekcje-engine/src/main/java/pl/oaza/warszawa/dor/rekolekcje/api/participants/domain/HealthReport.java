package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
class HealthReport {
  private String currentTreatment;
  private String medications;
  private String allergies;
  private String other;

  HealthReportValue value() {
    return HealthReportValue.builder()
        .currentTreatment(currentTreatment)
        .medications(medications)
        .allergies(allergies)
        .other(other)
        .build();
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HealthReportValue {
  private final String currentTreatment;
  private final String mentalDisorders;
  private final String medications;
  private final String allergies;
  private final String medicalDiet;
  private final Boolean canHike;
  private final String illnessHistory;
  private final Boolean hasMotionSickness;
  private final String other;
}

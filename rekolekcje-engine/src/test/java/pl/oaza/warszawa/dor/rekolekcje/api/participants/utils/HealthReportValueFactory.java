package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;

class HealthReportValueFactory {

  HealthReportValue withSampleData() {
    return HealthReportValue.builder()
        .currentTreatment("Broken leg")
        .build();
  }

  HealthReportValue withFullData() {
    return HealthReportValue.builder()
        .currentTreatment("Standard treatment for diabetes")
        .medications("Insuline")
        .allergies("Peanuts, lactose")
        .canHike(true)
        .hasMotionSickness(true)
        .illnessHistory("Hib implant injected")
        .mentalDisorders("None")
        .other("May be very weird sometimes")
        .build();
  }

  HealthReportValue withUpdatedData() {
    return HealthReportValue.builder()
        .currentTreatment("None")
        .medications("Gripex")
        .allergies("Milk")
        .canHike(false)
        .hasMotionSickness(true)
        .illnessHistory("Hib implant injected")
        .mentalDisorders("None")
        .other("Unstable")
        .build();
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.CurrentApplicationValue;

public class CurrentApplicationValueFactory {

  CurrentApplicationValue withSampleData() {
    return CurrentApplicationValue.builder()
        .stage("OND")
        .turn(1)
        .build();
  }

  CurrentApplicationValue withFullData() {
    return CurrentApplicationValue.builder()
        .stage("OND")
        .build();
  }

  CurrentApplicationValue withUpdatedData() {
    return CurrentApplicationValue.builder()
        .stage("ONŻ 1")
        .turn(3)
        .build();
  }
}

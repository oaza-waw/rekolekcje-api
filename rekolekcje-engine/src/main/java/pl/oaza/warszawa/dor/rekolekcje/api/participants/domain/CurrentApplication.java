package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.CurrentApplicationValue;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
class CurrentApplication {

  private String stage;
  private Integer turn;

  CurrentApplicationValue value() {
    return CurrentApplicationValue.builder()
        .stage(stage)
        .turn(turn)
        .build();
  }
}

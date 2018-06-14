package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentApplicationValue {

  private final String stage;
  private final Integer turn;

}

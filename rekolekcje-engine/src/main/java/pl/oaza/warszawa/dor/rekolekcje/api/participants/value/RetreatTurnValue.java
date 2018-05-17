package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RetreatTurnValue {
  private final String stage;
  private final String location;
  private final Integer year;
}

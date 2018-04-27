package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressValue {
  private final String street;
  private final Integer number;
  private final Integer flat;
  private final String code;
  private final String city;
}

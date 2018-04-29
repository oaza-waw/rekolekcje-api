package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressValue {
  private final String streetName;
  private final Integer streetNumber;
  private final Integer flatNumber;
  private final String postalCode;
  private final String city;
}

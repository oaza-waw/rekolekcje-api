package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Address {
  private String street;
  private Integer streetNumber;
  private Integer flatNumber;
  private String postalCode;
  private String city;

  AddressValue value() {
    return AddressValue.builder()
        .city(city)
        .postalCode(postalCode)
        .flatNumber(flatNumber)
        .streetNumber(streetNumber)
        .streetName(street)
        .build();
  }
}

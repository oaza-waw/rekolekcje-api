package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Builder
@Getter
public class ParishData {
  private final Long id;
  private final String name;
  private final String address;
}

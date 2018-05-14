package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ParishData {
  private final Long id;
  private final String name;
  private final String address;
  private final Long region;
}

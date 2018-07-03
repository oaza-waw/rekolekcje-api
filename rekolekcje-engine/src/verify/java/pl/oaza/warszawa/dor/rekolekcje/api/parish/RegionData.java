package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import lombok.*;

@Builder
@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
public class RegionData {

  private final Long id;
  private final String name;

}

package pl.oaza.warszawa.dor.rekolekcje.api.region.dto;

import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Builder
@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
public class RegionDTO {

  private final long id;
  private final String name;

}

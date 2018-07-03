package pl.oaza.warszawa.dor.rekolekcje.api.parish.dto;

import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Builder
@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
public class RegionDTO {

  private final Long id;
  private final String name;

}

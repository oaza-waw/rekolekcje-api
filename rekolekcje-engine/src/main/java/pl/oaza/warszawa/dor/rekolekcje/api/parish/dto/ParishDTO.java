package pl.oaza.warszawa.dor.rekolekcje.api.parish.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public final class ParishDTO {

  private final Long id;
  private final String name;
  private final String address;
}

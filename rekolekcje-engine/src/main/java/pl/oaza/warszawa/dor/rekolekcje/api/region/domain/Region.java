package pl.oaza.warszawa.dor.rekolekcje.api.region.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.region.dto.RegionDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Region {
  @Id
  private long id;
  private String name;

  RegionDTO dto() {
    return RegionDTO.builder()
            .id(id)
            .name(name)
            .build();
  }
}

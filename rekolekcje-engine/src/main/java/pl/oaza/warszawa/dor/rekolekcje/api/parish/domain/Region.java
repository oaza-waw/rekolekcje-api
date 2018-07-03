package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.RegionDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Region {
  @Wither
  @Id
  private Long id;
  private String name;

  static Region fromDTO(RegionDTO dto) {
    return new Region(dto.getId(), dto.getName());
  }

  RegionDTO dto() {
    return RegionDTO.builder()
            .id(id)
            .name(name)
            .build();
  }
}

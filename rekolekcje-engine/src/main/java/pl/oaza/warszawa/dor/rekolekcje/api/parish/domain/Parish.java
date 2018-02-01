package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Parish {

  @Id
  private Long id;
  private String name;
  private String address;

  public ParishDTO dto() {
    return ParishDTO.builder()
        .id(id)
        .name(name)
        .address(address)
        .build();
  }
}

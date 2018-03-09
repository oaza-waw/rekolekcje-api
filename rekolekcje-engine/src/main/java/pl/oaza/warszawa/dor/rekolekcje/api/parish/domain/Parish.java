package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Parish {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String name;
  private String address;
  private Long region;

  public ParishDTO dto() {
    return ParishDTO.builder()
        .id(id)
        .name(name)
        .address(address)
        .region(region)
        .build();
  }
}

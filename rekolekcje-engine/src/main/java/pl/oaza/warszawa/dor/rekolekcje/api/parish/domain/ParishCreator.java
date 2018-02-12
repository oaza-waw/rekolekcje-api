package pl.oaza.warszawa.dor.rekolekcje.api.parish.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

class ParishCreator {
  Parish from(ParishDTO parishDTO) {
    return Parish.builder()
        .id(parishDTO.getId())
        .name(parishDTO.getName())
        .address(parishDTO.getAddress())
        .build();
  }
}

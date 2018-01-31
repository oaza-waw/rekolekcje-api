package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParishDTO;

public class ParishFactory {

  public static ParishDTO sampleParish = ParishDTO.builder()
      .name("Sample parish")
      .address("Sample parish address")
      .build();
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParentsDTO {
  String motherName;
  String fatherName;
}

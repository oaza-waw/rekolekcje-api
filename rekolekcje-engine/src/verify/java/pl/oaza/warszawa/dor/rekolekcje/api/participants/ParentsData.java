package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParentsData {
  String motherName;
  String fatherName;
}

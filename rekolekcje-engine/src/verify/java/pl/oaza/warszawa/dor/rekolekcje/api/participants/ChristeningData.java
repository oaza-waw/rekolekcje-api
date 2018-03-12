package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ChristeningData {
  String place;
  LocalDate date;
}

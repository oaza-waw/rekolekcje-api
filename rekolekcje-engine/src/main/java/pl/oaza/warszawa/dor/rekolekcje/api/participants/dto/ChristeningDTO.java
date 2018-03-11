package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ChristeningDTO {
  String place;
  LocalDate date;
}

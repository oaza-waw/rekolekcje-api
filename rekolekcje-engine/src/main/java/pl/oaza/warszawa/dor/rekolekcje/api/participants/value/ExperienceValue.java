package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.List;

@Value
@Builder
public class ExperienceValue {
  private final List<String> summerRetreats;
  private final String kwcStatus;
  private final ZonedDateTime kwcSince;
}

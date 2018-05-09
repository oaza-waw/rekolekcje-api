package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder
public class ExperienceValue {
  private final String kwcStatus;
  private final ZonedDateTime kwcSince;
  private final Integer numberOfSummerRetreats;
  private final Integer numberOfPrayerRetreats;
}

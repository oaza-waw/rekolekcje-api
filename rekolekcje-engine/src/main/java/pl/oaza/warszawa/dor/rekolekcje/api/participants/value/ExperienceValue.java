package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import java.time.ZonedDateTime;
import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExperienceValue {
  private final String kwcStatus;
  private final ZonedDateTime kwcSince;
  private final Integer numberOfCommunionDays;
  private final Integer numberOfPrayerRetreats;
  private Integer formationMeetingsInMonth;
  private String leadingGroupToFormationStage;
  private Integer deuterocatechumenateYear;
  private Integer stepsTaken;
  private Integer stepsPlannedThisYear;
  private Integer celebrationsTaken;
  private Integer celebrationsPlannedThisYear;
  private Set<RetreatTurnValue> historicalRetreats;
}

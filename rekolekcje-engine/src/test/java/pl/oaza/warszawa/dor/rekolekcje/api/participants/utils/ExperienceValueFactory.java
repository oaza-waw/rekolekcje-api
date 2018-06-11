package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import com.google.common.collect.Sets;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

class ExperienceValueFactory {

  private static final Set<RetreatTurnValue> historicalTurns = Sets.newHashSet(
      RetreatTurnValue.builder()
          .stage("OND")
          .location("In the middle of nowhere")
          .year(1990)
          .build(),
      RetreatTurnValue.builder()
          .stage("ONŻ 1")
          .location("High Mountains")
          .year(2013)
          .build()
  );

  private final ExperienceValue.ExperienceValueBuilder sampleExperienceBuilder = ExperienceValue.builder()
      .kwcStatus("Active");

  ExperienceValue withSampleData() {
    return sampleExperienceBuilder.build();
  }

  ExperienceValue withFullData() {
    return ExperienceValue.builder()
        .kwcStatus("Active")
        .kwcSince(ZonedDateTime.of(LocalDateTime.of(1995, 5, 4, 12, 0), ZoneId.of("UTC")))
        .numberOfCommunionDays(3)
        .numberOfPrayerRetreats(5)
        .leadingGroupToFormationStage("ONŻ I")
        .formationMeetingsInMonth(3)
        .celebrationsTaken(5)
        .celebrationsPlannedThisYear(5)
        .stepsTaken(4)
        .stepsPlannedThisYear(6)
        .deuterocatechumenateYear(2016)
        .historicalRetreats(historicalTurns)
        .build();
  }

  ExperienceValue withHistoricalRetreats(Set<RetreatTurnValue> historicalRetreats) {
    return sampleExperienceBuilder
        .historicalRetreats(historicalRetreats)
        .build();
  }
}

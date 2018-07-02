package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import com.google.common.collect.ImmutableSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

class ExperienceValueFactory {

  private static final Set<RetreatTurnValue> historicalTurns = ImmutableSet.of(
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

  private static final Set<RetreatTurnValue> updatedHistoricalTurns = ImmutableSet.of(
      RetreatTurnValue.builder()
          .stage("ONŻ 1")
          .location("Far away")
          .year(2014)
          .build(),
      RetreatTurnValue.builder()
          .stage("ONŻ 2")
          .location("Further away")
          .year(2015)
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
    return ExperienceValue.builder()
        .kwcStatus("Active")
        .historicalRetreats(historicalRetreats)
        .build();
  }

  ExperienceValue withUpdatedData() {
    return ExperienceValue.builder()
        .kwcStatus("Member")
        .kwcSince(ZonedDateTime.of(LocalDateTime.of(1999, 5, 4, 12, 0), ZoneId.of("UTC")))
        .numberOfCommunionDays(2)
        .numberOfPrayerRetreats(4)
        .leadingGroupToFormationStage("ODB")
        .formationMeetingsInMonth(2)
        .celebrationsTaken(10)
        .celebrationsPlannedThisYear(0)
        .stepsTaken(5)
        .stepsPlannedThisYear(2)
        .deuterocatechumenateYear(2013)
        .build();
  }

  static ExperienceValueCloner from(ExperienceValue original) {
    return new ExperienceValueCloner(original);
  }

  public static class ExperienceValueCloner {

    private ExperienceValue.ExperienceValueBuilder builder;

    private ExperienceValueCloner(ExperienceValue original) {
      this.builder = ExperienceValue.builder()
          .historicalRetreats(original.getHistoricalRetreats())
          .kwcStatus(original.getKwcStatus())
          .kwcSince(original.getKwcSince())
          .numberOfPrayerRetreats(original.getNumberOfPrayerRetreats())
          .numberOfCommunionDays(original.getNumberOfCommunionDays())
          .celebrationsTaken(original.getCelebrationsTaken())
          .celebrationsPlannedThisYear(original.getCelebrationsPlannedThisYear())
          .deuterocatechumenateYear(original.getDeuterocatechumenateYear())
          .stepsTaken(original.getStepsTaken())
          .stepsPlannedThisYear(original.getStepsPlannedThisYear())
          .formationMeetingsInMonth(original.getFormationMeetingsInMonth())
          .leadingGroupToFormationStage(original.getLeadingGroupToFormationStage());
    }

    ExperienceValueCloner withHistoricalRetreats(Set<RetreatTurnValue> historicalRetreats) {
      this.builder = this.builder.historicalRetreats(historicalRetreats);
      return this;
    }

    public ExperienceValue clone() {
      return builder.build();
    }
  }
}

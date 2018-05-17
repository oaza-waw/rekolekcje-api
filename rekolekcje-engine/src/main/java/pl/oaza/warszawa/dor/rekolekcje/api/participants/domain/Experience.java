package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

import static pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.DateConverter.convertToUtc;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
class Experience {
  private LocalDateTime kwcSince;
  private String kwcStatus;
  private Integer numberOfCommunionDays;
  private Integer numberOfPrayerRetreats;
  private Integer formationMeetingsInMonth;
  private String leadingGroupToFormationStage;
  private Integer deuterocatechumenateYear;
  private Integer stepsTaken;
  private Integer stepsPlannedThisYear;
  private Integer celebrationsTaken;
  private Integer celebrationsPlannedThisYear;

  ExperienceValue value() {
    return ExperienceValue.builder()
        .kwcSince(convertToUtc(kwcSince))
        .kwcStatus(kwcStatus)
        .numberOfCommunionDays(numberOfCommunionDays)
        .numberOfPrayerRetreats(numberOfPrayerRetreats)
        .formationMeetingsInMonth(formationMeetingsInMonth)
        .leadingGroupToFormationStage(leadingGroupToFormationStage)
        .deuterocatechumenateYear(deuterocatechumenateYear)
        .stepsTaken(stepsTaken)
        .stepsPlannedThisYear(stepsPlannedThisYear)
        .celebrationsTaken(celebrationsTaken)
        .celebrationsPlannedThisYear(celebrationsPlannedThisYear)
        .build();
  }
}

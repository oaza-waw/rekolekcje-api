package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

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
}

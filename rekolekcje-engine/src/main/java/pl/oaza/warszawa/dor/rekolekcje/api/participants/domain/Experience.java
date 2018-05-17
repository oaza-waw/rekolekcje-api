package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

  @OneToMany(mappedBy = "participantId")
  private List<RetreatTurn> historicalRetreats;

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
        .historicalRetreats(mapToValue())
        .build();
  }

  private List<RetreatTurnValue> mapToValue() {
    if (historicalRetreats == null) return null;

    return historicalRetreats.stream().map(RetreatTurn::value).collect(Collectors.toList());
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;
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

  @Builder.Default
  @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<RetreatTurn> historicalRetreats = Sets.newHashSet();

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

  private Set<RetreatTurnValue> mapToValue() {
    if (historicalRetreats == null || historicalRetreats.isEmpty()) {
      return null;
    }

    return historicalRetreats.stream()
        .map(RetreatTurn::value)
        .collect(Collectors.toSet());
  }

  void connectParticipant(Participant participant) {
    historicalRetreats.forEach(it -> it.setParticipant(participant));
  }
}

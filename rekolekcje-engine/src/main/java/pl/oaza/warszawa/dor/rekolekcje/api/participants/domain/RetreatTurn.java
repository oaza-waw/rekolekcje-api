package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
class RetreatTurn {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "participant_id", nullable = false)
  private Participant participant;

  private String stage;
  private String location;
  private Integer year;

  RetreatTurnValue value() {
    return RetreatTurnValue.builder()
        .id(id)
        .stage(stage)
        .location(location)
        .year(year)
        .build();
  }
}

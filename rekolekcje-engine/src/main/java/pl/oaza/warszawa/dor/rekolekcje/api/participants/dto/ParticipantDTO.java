package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Immutable
@Builder
@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
public final class ParticipantDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private Long pesel;
  private Long parishId;
  private String address;
  private ParentsDTO parents;

}

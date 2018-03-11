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

  private final Long id;
  private final String firstName;
  private final String lastName;
  private final Long pesel;
  private final Long parishId;
  private final String address;
  private final ParentsDTO parents;
  private final ChristeningDTO christening;

}

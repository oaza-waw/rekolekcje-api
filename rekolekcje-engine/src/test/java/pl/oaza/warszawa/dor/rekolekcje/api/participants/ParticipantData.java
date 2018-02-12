package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ParticipantData {
  private Long id;
  private String firstName;
  private String lastName;
  private Long pesel;
  private Long parishId;
  private String address;
}

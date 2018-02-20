package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

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

  public ParticipantDTO dto() {
    return ParticipantDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .address(address)
        .pesel(pesel)
        .parishId(parishId)
        .build();
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParentsDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Participant {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String firstName;
  private String lastName;
  private Long pesel;
  private Long parishId;
  private String address;
  private String motherName;
  private String fatherName;

  ParticipantDTO dto() {
    ParentsDTO parents = mapParentsToDTO();
    return ParticipantDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .parishId(parishId)
        .address(address)
        .parents(parents)
        .build();
  }

  private ParentsDTO mapParentsToDTO() {
    if (motherName != null || fatherName != null) {
      return ParentsDTO.builder()
          .motherName(motherName)
          .fatherName(fatherName)
          .build();
    } else {
      return null;
    }
  }
}

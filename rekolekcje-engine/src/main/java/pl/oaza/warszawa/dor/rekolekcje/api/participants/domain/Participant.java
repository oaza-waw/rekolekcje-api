package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ChristeningDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParentsDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

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
  private String christeningPlace;
  private LocalDate christeningDate;

  ParticipantDTO dto() {
    ParentsDTO parents = mapParentsToDTO();
    ChristeningDTO christening = mapChristeningToDTO();
    return ParticipantDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .parishId(parishId)
        .address(address)
        .parents(parents)
        .christening(christening)
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

  private ChristeningDTO mapChristeningToDTO() {
    if (christeningDate != null || christeningPlace != null) {
      return ChristeningDTO.builder()
          .place(christeningPlace)
          .date(christeningDate)
          .build();
    } else {
      return null;
    }
  }
}

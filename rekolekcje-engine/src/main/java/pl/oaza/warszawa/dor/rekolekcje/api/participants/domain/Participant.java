package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
  private LocalDateTime christeningDate;

  ParticipantDTO dto() {
    return ParticipantDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .parishId(parishId)
        .address(address)
        .fatherName(fatherName)
        .motherName(motherName)
        .christeningDate(convertToUtc(christeningDate))
        .christeningPlace(christeningPlace)
        .build();
  }

  private ZonedDateTime convertToUtc(LocalDateTime dateTime) {
    return dateTime != null ? ZonedDateTime.of(dateTime, ZoneId.of("UTC")) : null;
  }
}

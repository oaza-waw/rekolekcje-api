package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Immutable
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public final class ParticipantDTO {

  private long id;
  private String firstName;
  private String lastName;
  private long pesel;
  private long parishId;
  private String address;

  public long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public long getPesel() {
    return pesel;
  }

  public long getParishId() {
    return parishId;
  }

  public String getAddress() {
    return address;
  }

  public ParticipantDTO copyWithId(long newId) {
    return ParticipantDTO.builder()
        .id(newId)
        .firstName(firstName)
        .lastName(lastName)
        .address(address)
        .pesel(pesel)
        .parishId(parishId)
        .build();
  }
}

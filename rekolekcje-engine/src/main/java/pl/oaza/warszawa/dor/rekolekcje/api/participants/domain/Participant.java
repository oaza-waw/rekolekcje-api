package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Participant {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String firstName;
  private String lastName;
  private int pesel;
  private String parish;
  private String address;

  @SuppressWarnings("unused")
  public Participant() {
    // used by JPA
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getPesel() {
    return pesel;
  }

  public String getParish() {
    return parish;
  }

  public String getAddress() {
    return address;
  }

  Participant(ParticipantDTO dto) {
    this.id = dto.getId();
    this.firstName = dto.getFirstName();
    this.lastName = dto.getLastName();
    this.pesel = dto.getPesel();
    this.parish = dto.getParish();
    this.address = dto.getAddress();
  }

  ParticipantDTO dto() {
    return ParticipantDTO.builder(firstName, lastName)
        .id(id)
        .pesel(pesel)
        .parish(parish)
        .address(address)
        .build();
  }
}

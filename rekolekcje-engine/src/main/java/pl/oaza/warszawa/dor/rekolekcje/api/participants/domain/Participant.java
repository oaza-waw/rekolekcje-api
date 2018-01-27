package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
class Participant {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String firstName;
  private String lastName;
  private long pesel;

  @ManyToOne
  private Parish parish;
  private String address;

  @SuppressWarnings("unused")
  public Participant() {
    // used by JPA
  }

  long getId() {
    return id;
  }

  void setId(long id) {
    this.id = id;
  }

  Participant(ParticipantDTO dto) {
    this.id = dto.getId();
    this.firstName = dto.getFirstName();
    this.lastName = dto.getLastName();
    this.pesel = dto.getPesel();
    this.parish = new Parish(dto.getParish());
    this.address = dto.getAddress();
  }

  ParticipantDTO dto() {
    return ParticipantDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .parish(parish.dto())
        .address(address)
        .build();
  }
}

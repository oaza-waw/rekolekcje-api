package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParishDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
  @JoinColumn(name = "parish_id")
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

  void setParish(Parish parish) {
    this.parish = parish;
  }

  Participant(ParticipantDTO dto) {
    this.id = dto.getId();
    this.firstName = dto.getFirstName();
    this.lastName = dto.getLastName();
    this.pesel = dto.getPesel();
    if (dto.getParish() != null) {
    this.parish = new Parish(dto.getParish());
//    this.parish = dto.getParish();
    }
    this.address = dto.getAddress();
  }

  ParticipantDTO dto() {
    ParishDTO parishDTO = this.parish == null ? null : this.parish.dto();
    return ParticipantDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .pesel(pesel)
        .parish(parishDTO)
        .address(address)
        .build();
  }
}

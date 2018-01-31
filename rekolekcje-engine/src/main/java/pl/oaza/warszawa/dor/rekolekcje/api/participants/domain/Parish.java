package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParishDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
class Parish {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "parish_id")
  private long id;
  private String name;
  private String address;

  @OneToMany(mappedBy = "parish", cascade = CascadeType.ALL)
  private Set<Participant> participants = new HashSet<>();

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public Set<Participant> getParticipants() {
    return participants;
  }

  Parish() {
    // used by JPA
  }

  Parish(ParishDTO dto) {
    this.id = dto.getId();
    this.name = dto.getName();
    this.address = dto.getAddress();
  }

  ParishDTO dto() {
    return ParishDTO.builder()
        .id(getId())
        .name(getName())
        .address(getAddress())
        .build();
  }
}

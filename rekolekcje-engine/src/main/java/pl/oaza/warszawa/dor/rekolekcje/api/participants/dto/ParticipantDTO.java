package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

import org.hibernate.annotations.Immutable;

import java.util.Objects;

@Immutable
public final class ParticipantDTO {

  private long id;
  private String firstName;
  private String lastName;
  private long pesel;
  private String parish;
  private String address;

  @SuppressWarnings("unused")
  ParticipantDTO() {
    // JSON serialization / deserialization
  }

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

  public String getParish() {
    return parish;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ParticipantDTO that = (ParticipantDTO) o;

    if (id != that.id) return false;
    if (pesel != that.pesel) return false;
    if (!Objects.equals(firstName, that.firstName)) return false;
    if (!Objects.equals(lastName, that.lastName)) return false;
    if (parish != null ? !parish.equals(that.parish) : that.parish != null) return false;
    return address != null ? address.equals(that.address) : that.address == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    result = 31 * result + (int) (pesel ^ (pesel >>> 32));
    result = 31 * result + (parish != null ? parish.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ParticipantDTO{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", pesel=" + pesel +
        ", parish='" + parish + '\'' +
        ", address='" + address + '\'' +
        '}';
  }

  private ParticipantDTO(ParticipantDTOBuilder builder) {
    this.id = builder.id;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.pesel = builder.pesel;
    this.parish = builder.parish;
    this.address = builder.address;
  }

  public static ParticipantDTOBuilder builder(String firstName, String lastName) {
    return new ParticipantDTOBuilder(firstName, lastName);
  }

  public static class ParticipantDTOBuilder {
    private long id;
    private String firstName;
    private String lastName;
    private long pesel;
    private String parish;
    private String address;

    private ParticipantDTOBuilder(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public ParticipantDTOBuilder id(long id) {
      this.id = id;
      return this;
    }

    public ParticipantDTOBuilder pesel(long pesel) {
      this.pesel = pesel;
      return this;
    }

    public ParticipantDTOBuilder parish(String parish) {
      this.parish = parish;
      return this;
    }

    public ParticipantDTOBuilder address(String address) {
      this.address = address;
      return this;
    }

    public ParticipantDTO build() {
      return new ParticipantDTO(this);
    }
  }
}

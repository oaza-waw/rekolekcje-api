package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public final class ParticipantDTO {

  private final long id;
  @NotNull private final String firstName;
  @NotNull private final String lastName;
  private final int pesel;
  private final String parish;
  private final String address;

  public long getId() {
    return id;
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
    result = 31 * result + pesel;
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
    private int pesel;
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

    public ParticipantDTOBuilder pesel(int pesel) {
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

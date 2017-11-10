package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

public class ParticipantDTO {

  private long id;
  private String firstName;
  private String lastName;
  private int pesel;
  private String parish;
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

  public int getPesel() {
    return pesel;
  }

  public String getParish() {
    return parish;
  }

  public String getAddress() {
    return address;
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

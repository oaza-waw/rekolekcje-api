package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

public class ParticipantNotFoundException extends RuntimeException {

  public ParticipantNotFoundException(long id) {
    super("No participant with id " + id + " found!");
  }

  public ParticipantNotFoundException(String firstName, String lastName, Long pesel) {
    super("No participant found with this data: " + firstName + " " + lastName + ", " + pesel);
  }
}

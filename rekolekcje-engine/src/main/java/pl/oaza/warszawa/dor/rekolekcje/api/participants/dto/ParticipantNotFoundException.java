package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

public class ParticipantNotFoundException extends RuntimeException {

  public ParticipantNotFoundException(long id) {
    super("No participant with id " + id + " found!");
  }
}

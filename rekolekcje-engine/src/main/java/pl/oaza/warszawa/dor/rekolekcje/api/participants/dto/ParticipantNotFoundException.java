package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

public class ParticipantNotFoundException extends RuntimeException {

  public ParticipantNotFoundException(String message) {
    super(message);
  }
}

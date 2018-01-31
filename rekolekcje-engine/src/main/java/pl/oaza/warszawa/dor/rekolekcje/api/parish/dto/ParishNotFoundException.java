package pl.oaza.warszawa.dor.rekolekcje.api.parish.dto;

public class ParishNotFoundException extends RuntimeException {

  public ParishNotFoundException(long id) {
    super("No parish with id " + id + " found!", null, false, false);
  }
}

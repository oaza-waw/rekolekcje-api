package pl.oaza.warszawa.dor.rekolekcje.api.region.dto;

public class RegionNotFoundException extends RuntimeException {
  public RegionNotFoundException(long id) {
    super("No region with id " + id + " found!");
  }
}

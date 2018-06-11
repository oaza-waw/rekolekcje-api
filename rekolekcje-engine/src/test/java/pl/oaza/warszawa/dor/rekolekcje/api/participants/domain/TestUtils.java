package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

public class TestUtils {

  public static String convertPeselToBirthDate(String pesel) {
    return PeselToBirthDateConverter.convertPeselToBirthDate(pesel);
  }
}

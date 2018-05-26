package pl.oaza.warszawa.dor.rekolekcje.api.participants.pesetUtil;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class PeselBirthDateConverterTest {

  private final String pesel = "90010103999";
  private final String correspondingBirthDate = "01.01.1990";

  private final String[] peselArray = {
      "93103002331",
      "00212011230",
      "85022831221",
      "05311033544",
      "99090912324"
  };

  private final String[] correspondingBirthDateArray = {
      "30.10.1993",
      "20.01.2000",
      "28.02.1985",
      "10.11.2005",
      "09.09.1999"
  };

  @Test
  public void shouldConversionMethodReturnString() {
    assertThat(PeselBirthDateConverter.convertPeselToBirthDate(pesel)).isNotEmpty();
  }

  @Test
  public void shouldConvertPeselToBirthDate() {
    assertThat(
        Arrays.stream(peselArray)
            .map(PeselBirthDateConverter::convertPeselToBirthDate).toArray())
        .isEqualTo(correspondingBirthDateArray);
  }
}

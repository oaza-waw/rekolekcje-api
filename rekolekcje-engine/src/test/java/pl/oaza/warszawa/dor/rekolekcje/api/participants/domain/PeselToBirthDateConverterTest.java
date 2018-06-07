package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class PeselToBirthDateConverterTest {

  @Parameter(value = 0)
  public String pesel;

  @Parameter(value = 1)
  public String correspondingBirthDate;

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"93103002331", "30.10.1993"},
        {"00212011230", "20.01.2000"},
        {"85022831221", "28.02.1985"},
        {"05311033544", "10.11.2005"},
        {"99090912324", "09.09.1999"}
    });
  }


  @Test
  public void shouldConversionMethodReturnNotNull() {
    assertThat(PeselToBirthDateConverter.convertPeselToBirthDate(pesel)).isNotEmpty();
  }

  @Test
  public void shouldConvertPeselToBirthDate() {
    assertThat(PeselToBirthDateConverter.convertPeselToBirthDate(pesel)).isEqualTo(correspondingBirthDate);
  }
}

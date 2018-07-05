package pl.oaza.warszawa.dor.rekolekcje.api.participants.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PeselToBirthDateConverterTest {

  @Parameter(value = 0)
  public String pesel;

  @Parameter(value = 1)
  public String correspondingBirthDate;

  @Parameters(name = "{index}: convert {0} to {1}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"93103002331", "30.10.1993"},
        {"00212011230", "20.01.2000"},
        {"85022831221", "28.02.1985"},
        {"05311033544", "10.11.2005"},
        {"44812233544", "22.01.1844"},
        {"91441533544", "15.04.2191"},
        {"25720933544", "09.12.2225"},
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

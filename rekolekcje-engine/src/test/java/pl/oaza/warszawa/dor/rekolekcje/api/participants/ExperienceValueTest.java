package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;

public class ExperienceValueTest {

  @Test
  public void equalsContractForExperienceValue() {
    EqualsVerifier.forClass(ExperienceValue.class)
        .verify();
  }
}

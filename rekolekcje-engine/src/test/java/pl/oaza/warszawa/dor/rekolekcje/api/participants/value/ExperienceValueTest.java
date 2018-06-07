package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class ExperienceValueTest {

  @Test
  public void equalsContractForExperienceValue() {
    EqualsVerifier.forClass(ExperienceValue.class)
        .verify();
  }
}

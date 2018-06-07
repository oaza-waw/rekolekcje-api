package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class RetreatTurnValueTest {

  @Test
  public void equalsContractForRetreatTurnValue() {
    EqualsVerifier.forClass(RetreatTurnValue.class)
        .verify();
  }

}

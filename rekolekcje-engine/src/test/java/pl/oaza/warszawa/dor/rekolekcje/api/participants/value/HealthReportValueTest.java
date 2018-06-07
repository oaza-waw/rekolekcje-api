package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class HealthReportValueTest {

  @Test
  public void equalsContractForHealthReportValue() {
    EqualsVerifier.forClass(HealthReportValue.class)
        .verify();
  }
}

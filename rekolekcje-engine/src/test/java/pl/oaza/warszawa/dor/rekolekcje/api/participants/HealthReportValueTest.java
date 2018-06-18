package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;

public class HealthReportValueTest {

  @Test
  public void equalsContractForHealthReportValue() {
    EqualsVerifier.forClass(HealthReportValue.class)
        .verify();
  }
}
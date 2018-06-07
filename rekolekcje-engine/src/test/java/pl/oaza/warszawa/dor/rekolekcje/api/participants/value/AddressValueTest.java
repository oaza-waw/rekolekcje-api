package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class AddressValueTest {

  @Test
  public void equalsContractForAddressValue() {
    EqualsVerifier.forClass(AddressValue.class)
        .verify();
  }
}

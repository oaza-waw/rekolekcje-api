package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;

public class AddressValueTest {

  @Test
  public void equalsContractForParticipantDTO() {
    EqualsVerifier.forClass(AddressValue.class)
        .verify();
  }
}

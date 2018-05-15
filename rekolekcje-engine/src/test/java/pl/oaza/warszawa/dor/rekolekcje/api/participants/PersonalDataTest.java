package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

public class PersonalDataTest {

  @Test
  public void equalsContractForPersonalData() {
    EqualsVerifier.forClass(PersonalData.class)
        .verify();
  }
}

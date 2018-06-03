package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import nl.jqno.equalsverifier.*;
import org.junit.*;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.*;

public class PersonalDataTest {

  @Test
  public void equalsContractForPersonalData() {
    EqualsVerifier.forClass(PersonalData.class)
        .withIgnoredFields("birthDate")
        .verify();
  }
}

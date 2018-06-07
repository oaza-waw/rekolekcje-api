package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class PersonalDataValueTest {

  @Test
  public void equalsContractForPersonalData() {
    EqualsVerifier.forClass(PersonalDataValue.class)
        .withIgnoredFields("birthDate")
        .verify();
  }
}

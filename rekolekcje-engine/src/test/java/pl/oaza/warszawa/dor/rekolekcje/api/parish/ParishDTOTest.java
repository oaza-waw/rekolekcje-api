package pl.oaza.warszawa.dor.rekolekcje.api.parish;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.parish.dto.ParishDTO;

public class ParishDTOTest {

  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(ParishDTO.class)
        .verify();
  }
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

public class ParticipantDTOTest {

  @Test
  public void equalsContractForParticipantDTO() {
    EqualsVerifier.forClass(ParticipantDTO.class)
        .withNonnullFields("firstName", "lastName")
        .verify();
  }
}

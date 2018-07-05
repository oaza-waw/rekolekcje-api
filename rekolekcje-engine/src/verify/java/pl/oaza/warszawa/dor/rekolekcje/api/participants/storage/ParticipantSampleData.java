package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParticipantSampleData {
  private final Long id;
  private final String firstName;
  private final String lastName;
  private final String pesel;
  private final Long parishId;

  // personal data
  private final LocalDateTime christeningDate;

  // address
  private final String postalCode;

  // health report
  private final String currentTreatment;

  // experience
  private final String kwcStatus;
  private final Integer numberOfCommunionDays;
}

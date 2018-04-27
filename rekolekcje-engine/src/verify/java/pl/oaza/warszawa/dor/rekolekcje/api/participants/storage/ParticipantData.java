package pl.oaza.warszawa.dor.rekolekcje.api.participants.storage;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ParticipantData {
  private final Long id;
  private final String firstName;
  private final String lastName;
  private final Long pesel;
  private final Long parishId;

  // personal data
  private final String fatherName;
  private final String motherName;
  private final String christeningPlace;
  private final LocalDateTime christeningDate;
  private final String closeRelativeName;
  private final Long closeRelativeNumber;

  // address
  private final String street;
  private final Integer streetNumber;
  private final Integer flatNumber;
  private final String postalCode;
  private final String city;
}

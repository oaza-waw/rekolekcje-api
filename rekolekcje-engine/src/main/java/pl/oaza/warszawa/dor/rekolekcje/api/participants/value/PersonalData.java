package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder
public class PersonalData {

  private final String fatherName;
  private final String motherName;
  private final String christeningPlace;
  private final ZonedDateTime christeningDate;
  private final String closeRelativeName;
  private final Long closeRelativeNumber;

}

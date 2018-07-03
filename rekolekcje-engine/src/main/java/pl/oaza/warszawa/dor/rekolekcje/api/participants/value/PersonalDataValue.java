package pl.oaza.warszawa.dor.rekolekcje.api.participants.value;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.domain.ParticipantSex;

import java.time.ZonedDateTime;

@Value
@Builder
@EqualsAndHashCode(exclude = "birthDate")
public class PersonalDataValue {

  private final String firstName;
  private final String lastName;
  private final String pesel;
  private final Long phoneNumber;
  private final String email;
  private final Long parishId;
  private final ParticipantSex sex;

  @Builder.Default
  private final AddressValue address = AddressValue.builder().build();

  private final String fatherName;
  private final String motherName;
  private final String birthDate;
  private final String christeningPlace;
  private final ZonedDateTime christeningDate;
  private final String emergencyContactName;
  private final Long emergencyContactNumber;
  private final String schoolYear;
  private final String nameDay;
  private final String communityName;
}

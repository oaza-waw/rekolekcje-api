package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ParticipantData {
  Long id;
  String firstName;
  String lastName;
  Long pesel;
  Long parishId;
  String address;
  String fatherName;
  String motherName;
  String christeningPlace;
  LocalDate christeningDate;
}

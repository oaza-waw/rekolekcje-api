package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.AddressValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalData;

@Immutable
@Builder
@EqualsAndHashCode
@ToString
@Getter
public final class ParticipantDTO {

  private final Long id;
  private final String firstName;
  private final String lastName;
  private final String pesel;
  private final Long parishId;

  @Builder.Default
  private final AddressValue address = AddressValue.builder().build();

  @Builder.Default
  private final PersonalData personalData = PersonalData.builder().build();

  @Builder.Default
  private final HealthReportValue healthReport = HealthReportValue.builder().build();
}

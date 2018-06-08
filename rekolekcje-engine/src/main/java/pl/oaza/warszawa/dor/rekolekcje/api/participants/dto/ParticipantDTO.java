package pl.oaza.warszawa.dor.rekolekcje.api.participants.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.HealthReportValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.PersonalDataValue;

@Immutable
@Builder
@EqualsAndHashCode
@ToString
@Getter
public final class ParticipantDTO {

  private final Long id;

  @Builder.Default
  private final PersonalDataValue personalData = PersonalDataValue.builder().build();

  @Builder.Default
  private final HealthReportValue healthReport = HealthReportValue.builder().build();

  @Builder.Default
  private final ExperienceValue experience = ExperienceValue.builder().build();
}

package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import java.time.ZonedDateTime;
import java.util.Set;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.ExperienceValue;
import pl.oaza.warszawa.dor.rekolekcje.api.participants.value.RetreatTurnValue;

public class ParticipantFactory {

  private static final PersonalDataValueFactory personalDataFactory = new PersonalDataValueFactory();
  private static final ExperienceValueFactory experienceValueFactory = new ExperienceValueFactory();
  private static final HealthReportValueFactory healthReportValueFactory = new HealthReportValueFactory();
  private static final CurrentApplicationValueFactory currentApplicationValueFactory = new CurrentApplicationValueFactory();

  public static ParticipantDTO withMinimalData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .personalData(personalDataFactory.withMinimalData())
        .build();
  }

  public static ParticipantDTO withSampleData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .personalData(personalDataFactory.withSampleData())
        .experience(experienceValueFactory.withSampleData())
        .healthReport(healthReportValueFactory.withSampleData())
        .currentApplication(currentApplicationValueFactory.withSampleData())
        .build();
  }

  public static ParticipantDTO withSampleData(String firstName, String lastName, String pesel) {
    return ParticipantDTO.builder()
        .personalData(personalDataFactory.withSampleData(firstName, lastName, pesel))
        .experience(experienceValueFactory.withSampleData())
        .healthReport(healthReportValueFactory.withSampleData())
        .currentApplication(currentApplicationValueFactory.withSampleData())
        .build();
  }

  public static ParticipantDTO withSampleDataAndHistoricalRetreats(Long id, Set<RetreatTurnValue> historicalRetreats) {
    return ParticipantDTO.builder()
        .id(id)
        .personalData(personalDataFactory.withSampleData())
        .experience(experienceValueFactory.withHistoricalRetreats(historicalRetreats))
        .healthReport(healthReportValueFactory.withSampleData())
        .currentApplication(currentApplicationValueFactory.withSampleData())
        .build();
  }

  public static ParticipantDTO withChristeningDate(ZonedDateTime christeningDate) {
    return ParticipantDTO.builder()
        .personalData(personalDataFactory.withChristeningDate(christeningDate))
        .build();
  }

  public static ParticipantDTO withFullData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .personalData(personalDataFactory.withFullData())
        .experience(experienceValueFactory.withFullData())
        .healthReport(healthReportValueFactory.withFullData())
        .currentApplication(currentApplicationValueFactory.withFullData())
        .build();
  }

  public static ParticipantDTO withUpdatedData(Long id) {
    return ParticipantDTO.builder()
        .id(id)
        .personalData(personalDataFactory.withUpdatedData())
        .experience(experienceValueFactory.withUpdatedData())
        .healthReport(healthReportValueFactory.withUpdatedData())
        .currentApplication(currentApplicationValueFactory.withUpdatedData())
        .build();
  }

  static ParticipantDTO withBirthDate(ParticipantDTO original, String birthDate) {
    return ParticipantDTO.builder()
        .id(original.getId())
        .personalData(personalDataFactory.withBirthDate(original.getPersonalData(), birthDate))
        .healthReport(original.getHealthReport())
        .experience(original.getExperience())
        .currentApplication(original.getCurrentApplication())
        .build();
  }

  public static ParticipantCloner from(ParticipantDTO dto) {
    return new ParticipantCloner(dto);
  }

  public static class ParticipantCloner {
    private final ParticipantDTO original;

    private ParticipantDTO.ParticipantDTOBuilder builder;

    private ParticipantCloner(ParticipantDTO original) {
      this.original = original;
      this.builder = ParticipantDTO.builder()
          .id(original.getId())
          .personalData(original.getPersonalData())
          .experience(original.getExperience())
          .healthReport(original.getHealthReport())
          .currentApplication(original.getCurrentApplication());
    }

    public ParticipantCloner withId(Long id) {
      this.builder = this.builder.id(id);
      return this;
    }

    public ParticipantCloner withHistoricalRetreats(Set<RetreatTurnValue> historicalRetreats) {
      final ExperienceValue newExperienceValue = ExperienceValueFactory.from(original.getExperience())
          .withHistoricalRetreats(historicalRetreats)
          .clone();
      this.builder = this.builder.experience(newExperienceValue);
      return this;
    }

    public ParticipantDTO clone() {
      return builder.build();
    }
  }
}

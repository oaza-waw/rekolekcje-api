package pl.oaza.warszawa.dor.rekolekcje.api.participants.utils;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.time.ZonedDateTime;

public class ParticipantFactory {

  private static final PersonalDataValueFactory personalDataFactory = new PersonalDataValueFactory();
  private static final ExperienceValueFactory experienceValueFactory = new ExperienceValueFactory();
  private static final HealthReportValueFactory healthReportValueFactory = new HealthReportValueFactory();

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
        .build();
  }

  public static ParticipantDTO withSampleData(String firstName, String lastName, String pesel) {
    return ParticipantDTO.builder()
        .personalData(personalDataFactory.withSampleData(firstName, lastName, pesel))
        .experience(experienceValueFactory.withSampleData())
        .healthReport(healthReportValueFactory.withSampleData())
        .build();
  }

  public static ParticipantDTO withSampleData(Long id, String firstName, String lastName, String pesel) {
    return ParticipantDTO.builder()
        .id(id)
        .personalData(personalDataFactory.withSampleData(firstName, lastName, pesel))
        .experience(experienceValueFactory.withSampleData())
        .healthReport(healthReportValueFactory.withSampleData())
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
        .build();
  }

}

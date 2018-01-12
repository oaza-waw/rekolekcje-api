package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

public class ParticipantFactory {

  public static ParticipantDTO sampleParticipant() {
    return ParticipantDTO.builder()
        .firstName("Sample")
        .lastName("Participant")
        .address("Address Street no. 123")
        .pesel(98101012345L)
        .parish("Parish name and address")
        .build();
  }

  public static ParticipantDTO participantWithMinimalData() {
    return ParticipantDTO.builder()
        .firstName("Minimal")
        .lastName("Participant")
        .build();
  }

  public static ParticipantDTO participantWithAllData() {
    return ParticipantDTO.builder()
        .firstName("John")
        .lastName("Smith")
        .address("Street no. 987, City")
        .pesel(90042312345L)
        .parish("Name and address of parish")
        .build();
  }
}

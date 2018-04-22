package pl.oaza.warszawa.dor.rekolekcje.api.participants;

import pl.oaza.warszawa.dor.rekolekcje.api.participants.dto.ParticipantDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class ParticipantsTestData {

    public static final ParticipantDTO participantWithMinimalData = ParticipantDTO.builder()
            .firstName("Jack")
            .lastName("Frost")
            .pesel(93010100000L)
            .build();

    public static final ParticipantDTO participantWithFullData = ParticipantDTO.builder()
            .firstName("Paul")
            .lastName("Pierce")
            .pesel(987654L)
            .address("Boston")
            .parishId(1L)
            .motherName("Mary")
            .fatherName("Jake")
            .christeningPlace("Los Angeles")
            .christeningDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")))
            .build();

    public static final ParticipantDTO sampleParticipant1 = ParticipantDTO.builder()
            .firstName("Kevin")
            .lastName("Garnett")
            .pesel(82020354321L)
            .address("Boston TD Garden")
            .build();

    public static final ParticipantDTO sampleParticipant2 = ParticipantDTO.builder()
            .firstName("Ray")
            .lastName("Allen")
            .pesel(82020312345L)
            .address("TD Garden")
            .parishId(2L)
            .build();

    public static final ParticipantDTO getSampleParticipantWithHealthReport1 = ParticipantDTO.builder()
            .firstName("Ed")
            .lastName("Sheeran")
            .pesel(91010103434L)
            .currentTreatment("None")
            .medications("Amphetamine")
            .allergies("Stupidity")
            .other("None")
            .build();

    public static final ParticipantDTO getSampleParticipantWithVeryLongHealthReport1 = ParticipantDTO.builder()
            .firstName("Norman")
            .lastName("Stone")
            .pesel(93110304778L)
            .currentTreatment("Very long treatment history and some serious issues "
                    + "about heath status which describing takes more than 255 chars"
                    + "so I have to write a little more to meet my dreamed requirements"
                    + "almost there man... Do you think anybody would write some more? Nobody care about heath of participants")
            .medications("Amol")
            .allergies("Saint water")
            .other("None")
            .build();
}

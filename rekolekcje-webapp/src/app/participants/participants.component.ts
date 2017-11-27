import {Component, OnInit} from "@angular/core";
import {Participant} from "./participant.model";
import {MockParticipantsService} from "./mock-participants.service";

@Component({
  selector: 'participants-root',
  templateUrl: './participants.component.html'
})
export class ParticipantsComponent implements OnInit {

  title = 'All participants';

  participants: Participant[];
  selectedParticipant: Participant;

  constructor(private participantsService: MockParticipantsService) {}

  ngOnInit(): void {
    this.participantsService.findAll().subscribe((participants) => {
      this.participants = participants;
    });
  }

  onSelectParticipant(participant: Participant) {
    this.selectedParticipant = participant;
  }
}

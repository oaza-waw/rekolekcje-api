import { Component, OnInit } from '@angular/core';
import { Participant } from './participant.model';
import { MockParticipantsService } from './mock-participants.service';

@Component({
  selector: 'participants-root',
  templateUrl: './participants.component.html'
})
export class ParticipantsComponent implements OnInit {

  participants: Participant[];

  constructor(private participantsService: MockParticipantsService) {}

  ngOnInit(): void {
    this.participantsService
      .findAll()
      .subscribe(participants => this.participants = participants );
  }

  onAddNewParticipant(participant: Participant) {
    this.participantsService
      .add(participant)
      .subscribe(newParticipant => {
        this.participants = this.participants.concat(newParticipant);
      });
  }

  onDeleteSingleParticipant(participantId: number) {
    this.participantsService
      .deleteOne(participantId)
      .subscribe(idOfDeleted => {
        this.participants = this.participants.filter(p => p.id != idOfDeleted);
      });
  }
}

import { Component, OnInit } from '@angular/core';
import { Participant } from '../participant.model';
import { MockParticipantsService } from '../mock-participants.service';

@Component({
  selector: 'participants-add-new',
  templateUrl: './participants-add-new.component.html',
  styleUrls: ['./participants-add-new.component.css']
})
export class ParticipantsAddNewComponent implements OnInit {

  newParticipant: Participant;

  constructor(private participantsService: MockParticipantsService) {
  }

  ngOnInit() {
    this.newParticipant = { id: null, firstName: '', lastName: '' };
  }

  onSubmit() {
    this.participantsService.add(this.newParticipant);
    this.newParticipant = { id: null, firstName: '', lastName: '' };
  }
}

import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Participant } from '../../shared/model/participant.model';

@Component({
  selector: 'old-participants-add-new',
  templateUrl: './participants-add-new.component.html',
  styleUrls: ['./participants-add-new.component.css']
})
export class ParticipantsAddNewComponent implements OnInit {

  newParticipant: Participant;

  @Output() addParticipantEvent: EventEmitter<Participant> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
    this.newParticipant = {id: null, firstName: '', lastName: ''};
  }

  onSubmit(): void {
     this.addParticipantEvent.emit(this.newParticipant);
     this.newParticipant = {id: null, firstName: '', lastName: ''};
  }
}

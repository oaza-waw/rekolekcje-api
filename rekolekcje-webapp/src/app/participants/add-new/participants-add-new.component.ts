import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Participant} from "../participant.model";

@Component({
  selector: 'participants-add-new',
  templateUrl: './participants-add-new.component.html',
  styleUrls: ['./participants-add-new.component.css']
})
export class ParticipantsAddNewComponent implements OnInit {

  newParticipant: Participant;

  @Output() addParticipantEvent: EventEmitter<Participant> = new EventEmitter();

  constructor() { }

  ngOnInit() {
    this.newParticipant = {id: null, firstName: '', lastName: ''};
  }

  onSubmit() {
     this.addParticipantEvent.emit(this.newParticipant);
     this.newParticipant = {id: null, firstName: '', lastName: ''};
  }
}

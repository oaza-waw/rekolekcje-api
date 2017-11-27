import {Component, Input, OnInit} from '@angular/core';
import {Participant} from "../participant.model";

@Component({
  selector: 'participant-details',
  templateUrl: './participant-details.component.html',
  styleUrls: ['./participant-details.component.css']
})
export class ParticipantDetailsComponent implements OnInit {

  @Input() participant: Participant;

  constructor() { }

  ngOnInit() {
  }

}

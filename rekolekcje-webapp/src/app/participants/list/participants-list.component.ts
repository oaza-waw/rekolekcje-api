import { Component, Input, OnInit } from '@angular/core';
import { Participant } from '../participant.model';
import { MockParticipantsService } from '../mock-participants.service';

@Component({
  selector: 'participants-list',
  templateUrl: './participants-list.component.html',
  styleUrls: ['./participants-list.component.css']
})
export class ParticipantsListComponent implements OnInit {
  title = 'All participants';

  @Input() participants: Participant[];

  constructor() {}

  ngOnInit(): void {
  }
}

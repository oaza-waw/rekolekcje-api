import { Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Participant } from '../participant.model';
import { MockParticipantsService } from '../mock-participants.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'participant-details',
  templateUrl: './participant-details.component.html',
  styleUrls: ['./participant-details.component.css']
})
export class ParticipantDetailsComponent implements OnInit {

  participant: Participant;

  constructor(private participantsService: MockParticipantsService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit() {
    this.getParticipant();
  }

  private getParticipant() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.participantsService.find(id)
  }

  goBack(): void {
    this.location.back();
  }
}

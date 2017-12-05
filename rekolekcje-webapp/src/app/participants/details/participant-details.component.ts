import { Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Participant } from '../participant.model';
import { MockParticipantsService } from '../mock-participants.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'participant-details',
  templateUrl: './participant-details.component.html',
  styleUrls: ['./participant-details.component.css']
})
export class ParticipantDetailsComponent implements OnInit {

  participant: Participant;

  constructor(
    private participantsService: MockParticipantsService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.getParticipant();
  }

  private getParticipant() {
    const id = + this.route.snapshot.paramMap.get('id');
    this.participantsService
      .findOne(id)
      .subscribe(participant => this.participant = participant);
    // this.participant = this.route.paramMap
    //   .switchMap((params: ParamMap) =>
    //     this.participantsService.findOne(+params.get('id')));
  }

  goBack(): void {
    this.location.back();
  }
}

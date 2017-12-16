import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/takeUntil';
import { MockParticipantsService } from '../../mock-participants.service';
import { Participant } from '../../../shared/model/participant.model';

@Component({
  selector: 'participant-add-edit',
  templateUrl: './participant-add-edit.component.html',
  styleUrls: ['./participant-add-edit.component.css']
})
export class ParticipantAddEditComponent implements OnInit, OnDestroy {

  editing: boolean;
  id: number;
  participantToEdit: Participant;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  // @TODO get participant to edit from store
  constructor(private participantsService: MockParticipantsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params
      .takeUntil(this.ngUnsubscribe)
      .subscribe(params => {
        this.id = +params['id'];
        if (this.id) {
          this.editing = false;
        } else {
          this.editing = true;
        }
        this.participantsService.find(this.id)
          .takeUntil(this.ngUnsubscribe)
          .subscribe(data => {
            this.participantToEdit = data;
          });
      });
  }

  addParticipant(participant: Participant): void {
    // @TODO dispatch action to store
    this.participantsService.add(participant);
  }

  updateParticipant(participant: Participant): void {
    // @TODO dispatch action to store
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }
}

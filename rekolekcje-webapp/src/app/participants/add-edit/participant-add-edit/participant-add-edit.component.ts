import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/takeUntil';
import { Participant } from '../../models/participant.model';
import { Store } from '@ngrx/store';
import { Participants } from '../../store/participants-reducer';
import { ParticipantsSharedActions } from '../../store/participants-actions';
import { AppSelectors } from '../../../core/store/app-selectors';

@Component({
  selector: 'participant-add-edit',
  templateUrl: './participant-add-edit.component.html',
  styleUrls: ['./participant-add-edit.component.css']
})
export class ParticipantAddEditComponent implements OnInit, OnDestroy {

  editing: boolean;
  participantToEdit: Participant;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private store: Store<Participants.State>) { }

  ngOnInit(): void {
    this.store.select(AppSelectors.getSelectedParticipant)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((participant: Participant) => this.participantToEdit = participant);
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  addParticipant(participant: Participant): void {
    this.store.dispatch(new ParticipantsSharedActions.CreateParticipant(participant));
  }

  updateParticipant(participant: Participant): void {
    this.store.dispatch(new ParticipantsSharedActions.UpdateParticipant(participant));
  }
}

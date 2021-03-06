import { Component, OnDestroy, OnInit } from '@angular/core';
import { Participant } from './models/participant.model';
import { Subject } from 'rxjs/Subject';
import { Store } from '@ngrx/store';
import { Participants } from './store/participants-reducer';
import { Observable } from 'rxjs/Observable';
import { ParticipantsSharedActions } from './store/participants-actions';
import { AppSelectors } from '../core/store/app-selectors';
import { Router } from '@angular/router';
import { Parish } from '../parish/models/parish.model';
import { ParishSharedActions } from '../parish/store/parish-actions';

@Component({
  selector: 'participants-root',
  templateUrl: './participants.component.html'
})
export class ParticipantsComponent implements OnInit, OnDestroy {

  participants: Observable<Participant[]>;
  parishes: Observable<Parish[]>;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private router: Router, private store: Store<Participants.State>) {}

  ngOnInit(): void {
    this.store.dispatch(new ParticipantsSharedActions.LoadParticipantsList());
    this.store.dispatch(new ParishSharedActions.LoadParishList());
    this.participants = this.store.select(AppSelectors.getParticipantsList);
    this.parishes = this.store.select(AppSelectors.getParishList);
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onAddParticipantHandler(participant: Participant): void {
    this.store.dispatch(new ParticipantsSharedActions.CreateParticipant(participant));
  }

  onDeleteParticipantHandler(participantId: number): void {
    this.store.dispatch(new ParticipantsSharedActions.DeleteParticipant(participantId));
  }

  onEditParticipantHandler(participant: Participant): void {
    this.store.dispatch(new ParticipantsSharedActions.UpdateParticipant(participant));
  }

  onSelectParticipantHandler(participant: Participant): void {
    this.store.dispatch(new ParticipantsSharedActions.SelectParticipant(participant));
    setTimeout(() => this.router.navigateByUrl(`/participants/${participant.id}`), 0);
  }
}

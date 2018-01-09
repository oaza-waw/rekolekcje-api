import { Component, OnDestroy, OnInit } from '@angular/core';
import { Participant } from '../shared/models/participant.model';
import { Subject } from 'rxjs/Subject';
import { Store } from '@ngrx/store';
import { Participants } from '../core/store/participants/participants-reducer';
import { Observable } from 'rxjs/Observable';
import { ParticipantsSharedActions } from '../shared/store-shared/participants/participants-actions';
import { AppSelectors } from '../core/store/app-selectors';
import { Router } from '@angular/router';

@Component({
  selector: 'participants-root',
  templateUrl: './participants.component.html'
})
export class ParticipantsComponent implements OnInit, OnDestroy {

  participants: Observable<Participant[]>;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private router: Router, private store: Store<Participants.State>) {}

  ngOnInit(): void {
    this.store.dispatch(new ParticipantsSharedActions.LoadParticipantsList());
    this.participants = this.store.select(AppSelectors.getParticipantsList);
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

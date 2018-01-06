import { Action, Store } from '@ngrx/store';
import { Actions, Effect } from '@ngrx/effects';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Participants } from '../../../core/store/participants/participants-reducer';
import { ParticipantsSharedActions } from './participants-actions';
import 'rxjs/add/operator/switchMap';
import CreateParticipantSuccess = ParticipantsSharedActions.CreateParticipantSuccess;
import { of } from 'rxjs/observable/of';
import DeleteParticipantSuccess = ParticipantsSharedActions.DeleteParticipantSuccess;
import LoadParticipantsListSuccess = ParticipantsSharedActions.LoadParticipantsListSuccess;
import { MOCK_PARTICIPANTS } from '../../../mock-data/mock-participants';
import UpdateParticipantSuccess = ParticipantsSharedActions.UpdateParticipantSuccess;
import { Injectable } from '@angular/core';
import SelectParticipantSuccess = ParticipantsSharedActions.SelectParticipantSuccess;

@Injectable()
export class ParticipantsEffects {

  @Effect()
  CreateParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.CreateParticipant)
    .switchMap((action: ParticipantsSharedActions.CreateParticipant) =>  of(new CreateParticipantSuccess(action.payload)));

  @Effect()
  DeleteParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.DeleteParticipant)
    .switchMap((action: ParticipantsSharedActions.DeleteParticipant) => of(new DeleteParticipantSuccess(action.payload)));

  @Effect()
  LoadParticipantsList: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.LoadParticipantsList)
    .switchMap((action: ParticipantsSharedActions.LoadParticipantsList) => of(new LoadParticipantsListSuccess(MOCK_PARTICIPANTS)));

  @Effect()
  SelectParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.SelectParticipant)
    .switchMap((action: ParticipantsSharedActions.SelectParticipant) => of(new SelectParticipantSuccess(action.payload)));

  @Effect()
  UpdateParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.UpdateParticipant)
    .switchMap((action: ParticipantsSharedActions.UpdateParticipant) => of(new UpdateParticipantSuccess(action.payload)));

  constructor(private actions: Actions, private store: Store<Participants.State>, private http: HttpClient) {
  }
}

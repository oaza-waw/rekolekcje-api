import { Action, Store } from '@ngrx/store';
import { Actions, Effect } from '@ngrx/effects';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Participants } from './participants-reducer';
import { ParticipantsSharedActions } from './participants-actions';
import { of } from 'rxjs/observable/of';
import { Injectable } from '@angular/core';
import { Config } from '../../../config';
import { Participant } from 'app/participants/models/participant.model';
import { catchError, map, switchMap } from 'rxjs/operators';
import CreateParticipantSuccess = ParticipantsSharedActions.CreateParticipantSuccess;
import DeleteParticipantSuccess = ParticipantsSharedActions.DeleteParticipantSuccess;
import UpdateParticipantSuccess = ParticipantsSharedActions.UpdateParticipantSuccess;
import SelectParticipantSuccess = ParticipantsSharedActions.SelectParticipantSuccess;
import CreateParticipantFail = ParticipantsSharedActions.CreateParticipantFail;
import DeleteParticipantFail = ParticipantsSharedActions.DeleteParticipantFail;
import LoadParticipantsListSuccess = ParticipantsSharedActions.LoadParticipantsListSuccess;
import LoadParticipantsListFail = ParticipantsSharedActions.LoadParticipantsListFail;
import UpdateParticipantFail = ParticipantsSharedActions.UpdateParticipantFail;

@Injectable()
export class ParticipantsEffects {

  @Effect()
  CreateParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.CreateParticipant)
    .pipe(
      switchMap((action: ParticipantsSharedActions.CreateParticipant) =>
        this.http.post<Participant>(Config.endpoints.participantsModule, action.payload)
          .pipe(
            map(data => new CreateParticipantSuccess(data)),
            catchError(error => of(new CreateParticipantFail(error)))
          )
      )
    );

  @Effect()
  DeleteParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.DeleteParticipant)
    .pipe(
      switchMap((action: ParticipantsSharedActions.DeleteParticipant) =>
        this.http.delete<Participant>(Config.endpoints.participantsModule + '/' + action.payload)
          .pipe(
            map(data => new DeleteParticipantSuccess(action.payload)),
            catchError(error => of(new DeleteParticipantFail(error)))
          ))
    );

  @Effect()
  LoadParticipantsList: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.LoadParticipantsList)
    .pipe(
      switchMap((action: ParticipantsSharedActions.LoadParticipantsList) => {
        console.log('endpoint: ' + Config.endpoints.participantsModule);
        return this.http.get<Participant[]>(Config.endpoints.participantsModule)
          .pipe(
            map((data: Participant[]) => new LoadParticipantsListSuccess(data)),
            catchError(error => of(new LoadParticipantsListFail(error)))
          )
        }
      )
    );

  @Effect()
  SelectParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.SelectParticipant)
    .pipe(
      switchMap((action: ParticipantsSharedActions.SelectParticipant) => of(new SelectParticipantSuccess(action.payload)))
    );

  @Effect()
  UpdateParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.UpdateParticipant)
    .pipe(
      switchMap((action: ParticipantsSharedActions.UpdateParticipant) =>
        this.http.put<Participant>(Config.endpoints.participantsModule, action.payload)
          .pipe(
            map(data => new UpdateParticipantSuccess(data)),
            catchError(error => of(new UpdateParticipantFail(error)))
          ))
    );

  constructor(private actions: Actions, private store: Store<Participants.State>, private http: HttpClient) {
  }
}

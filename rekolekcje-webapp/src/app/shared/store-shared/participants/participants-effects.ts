import { Action, Store } from '@ngrx/store';
import { Actions, Effect } from '@ngrx/effects';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Participants } from '../../../core/store/participants/participants-reducer';
import { ParticipantsSharedActions } from './participants-actions';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import CreateParticipantSuccess = ParticipantsSharedActions.CreateParticipantSuccess;
import { of } from 'rxjs/observable/of';
import DeleteParticipantSuccess = ParticipantsSharedActions.DeleteParticipantSuccess;
import { MOCK_PARTICIPANTS } from '../../../mock-data/mock-participants';
import UpdateParticipantSuccess = ParticipantsSharedActions.UpdateParticipantSuccess;
import { Injectable } from '@angular/core';
import SelectParticipantSuccess = ParticipantsSharedActions.SelectParticipantSuccess;
import { Config } from '../../../../config';
import { Participant } from 'app/shared/models/participant.model';
import CreateParticipantFail = ParticipantsSharedActions.CreateParticipantFail;
import DeleteParticipantFail = ParticipantsSharedActions.DeleteParticipantFail;
import LoadParticipantsListSuccess = ParticipantsSharedActions.LoadParticipantsListSuccess;
import LoadParticipantsListFail = ParticipantsSharedActions.LoadParticipantsListFail;
import UpdateParticipantFail = ParticipantsSharedActions.UpdateParticipantFail;
import { AuthService } from '../../../auth/auth.service';

@Injectable()
export class ParticipantsEffects {

  private options: any;

  @Effect()
  CreateParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.CreateParticipant)
    .switchMap((action: ParticipantsSharedActions.CreateParticipant) =>
      this.http.post<Participant>(Config.endpoints.participantsModule, action.payload)
        .map(data => new CreateParticipantSuccess(data))
        .catch(error => of(new CreateParticipantFail(error))));

  @Effect()
  DeleteParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.DeleteParticipant)
    .switchMap((action: ParticipantsSharedActions.DeleteParticipant) =>
      this.http.delete<Participant>(Config.endpoints.participantsModule + '/' + action.payload)
        .map(data => new DeleteParticipantSuccess(action.payload))
        .catch(error => of(new DeleteParticipantFail(error))));

  // Uncomment if you want data from mock data component
  // @Effect()
  // LoadParticipantsList: Observable<Action> = this.actions
  //   .ofType(ParticipantsSharedActions.types.LoadParticipantsList)
  //   .switchMap((action: ParticipantsSharedActions.LoadParticipantsList) => of(new LoadParticipantsListSuccess(MOCK_PARTICIPANTS)));
  @Effect()
  LoadParticipantsList: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.LoadParticipantsList)
    .switchMap((action: ParticipantsSharedActions.LoadParticipantsList) =>
      this.http.get<Participant[]>(Config.endpoints.participantsModule)
        .map((data: Participant[]) => new LoadParticipantsListSuccess(data))
        .catch(error => of(new LoadParticipantsListFail(error))));

  @Effect()
  SelectParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.SelectParticipant)
    .switchMap((action: ParticipantsSharedActions.SelectParticipant) => of(new SelectParticipantSuccess(action.payload)));

  @Effect()
  UpdateParticipant: Observable<Action> = this.actions
    .ofType(ParticipantsSharedActions.types.UpdateParticipant)
    .switchMap((action: ParticipantsSharedActions.UpdateParticipant) =>
      this.http.put<Participant>(Config.endpoints.participantsModule, action.payload)
        .map(data => new UpdateParticipantSuccess(data))
        .catch(error => of(new UpdateParticipantFail(error))));

  constructor(private actions: Actions, private store: Store<Participants.State>, private http: HttpClient, private authService: AuthService) {
    let headers = new HttpHeaders({'Authorization': 'Bearer ' + this.authService.getToken()});
    this.options = {headers: headers};
  }
}

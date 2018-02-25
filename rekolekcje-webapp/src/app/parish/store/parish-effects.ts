import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';
import { Observable } from 'rxjs/Observable';
import { Action, Store } from '@ngrx/store';
import { Parishes } from './parish-reducer';
import { HttpClient } from '@angular/common/http';
import { ParishSharedActions } from './parish-actions';
import { catchError, map, switchMap } from 'rxjs/operators';
import { Config } from '../../../config';
import CreateParishSuccess = ParishSharedActions.CreateParishSuccess;
import { of } from 'rxjs/observable/of';
import CreateParishFail = ParishSharedActions.CreateParishFail;
import LoadParishListSuccess = ParishSharedActions.LoadParishListSuccess;
import LoadParishListFail = ParishSharedActions.LoadParishListFail;
import { Parish } from '../models/parish.model';
import DeleteParishSuccess = ParishSharedActions.DeleteParishSuccess;
import DeleteParishFail = ParishSharedActions.DeleteParishFail;
import UpdateParishSuccess = ParishSharedActions.UpdateParishSuccess;
import UpdateParishFail = ParishSharedActions.UpdateParishFail;
import SelectParishSuccess = ParishSharedActions.SelectParishSuccess;

@Injectable()
export class ParishEffects {

  constructor(private actions: Actions, private store: Store<Parishes.State>, private http: HttpClient) {
  }

  @Effect()
  CreateParish: Observable<Action> = this.actions
    .ofType(ParishSharedActions.types.CreateParish)
    .pipe(
      switchMap((action: ParishSharedActions.CreateParish) =>
        this.http.post<Parish>(Config.endpoints.parishModule, action.payload)
          .pipe(
            map(data => new CreateParishSuccess(data)),
            catchError(error => of(new CreateParishFail(error)))
          )
      )
    );

  @Effect()
  LoadParishList: Observable<Action> = this.actions
    .ofType(ParishSharedActions.types.LoadParishList)
    .pipe(
      switchMap((action: ParishSharedActions.LoadParishList) =>
        this.http.get<Parish[]>(Config.endpoints.parishModule)
          .pipe(
            map((data: Parish[]) => new LoadParishListSuccess(data)),
            catchError(error => of(new LoadParishListFail(error)))
          )
      )
    );

  @Effect()
  DeleteParish: Observable<Action> = this.actions
    .ofType(ParishSharedActions.types.DeleteParish)
    .pipe(
      switchMap((action: ParishSharedActions.DeleteParish) =>
        this.http.delete<Parish>(Config.endpoints.parishModule + '/' + action.payload)
          .pipe(
            map(data => new DeleteParishSuccess(action.payload)),
            catchError(error => of(new DeleteParishFail(error)))
          )
      )
    );

  @Effect()
  UpdateParish: Observable<Action> = this.actions
    .ofType(ParishSharedActions.types.UpdateParish)
    .pipe(
      switchMap((action: ParishSharedActions.UpdateParish) =>
        this.http.put<Parish>(Config.endpoints.parishModule, action.payload)
          .pipe(
            map(data => new UpdateParishSuccess(data)),
            catchError(error => of(new UpdateParishFail(error)))
          )
      )
    );

  @Effect()
  SelectParish: Observable<Action> = this.actions
    .ofType(ParishSharedActions.types.SelectParish)
    .pipe(
      switchMap((action: ParishSharedActions.SelectParish) => of(new SelectParishSuccess(action.payload)))
    );
}

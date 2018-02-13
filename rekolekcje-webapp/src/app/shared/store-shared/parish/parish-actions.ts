import { Action } from '@ngrx/store';
import { Parish } from '../../models/parish.model';

export namespace ParishSharedActions {
  export const types = {
    CreateParish: '[Parish] Create Parish',
    CreateParishFail: '[Parish] Create Parish Fail',
    CreateParishSuccess: '[Parish] Create Parish Success',
    LoadParishList: '[Parish] Load Parish List',
    LoadParishListFail: '[Parish] Load Parish List Fail',
    LoadParishListSuccess: '[Parish] Load Parish List Success',
  };

  export class CreateParish implements Action {
    type = types.CreateParish;

    constructor(public payload: Parish) { }
  }

  export class CreateParishSuccess implements Action {
    type = types.CreateParishSuccess;

    constructor(public payload: Parish) { }
  }

  export class CreateParishFail implements Action {
    type = types.CreateParishFail;

    constructor(public payload: any) { }
  }

  export class LoadParishList implements Action {
    type = types.LoadParishList;

    constructor(public payload?: any) { }
  }

  export class LoadParishListSuccess implements Action {
    type = types.LoadParishListSuccess;

    constructor(public payload: Parish[]) { }
  }

  export class LoadParishListFail implements Action {
    type = types.LoadParishListFail;

    constructor(public payload: any) { }
  }
}

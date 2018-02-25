import { Action } from '@ngrx/store';
import { Parish } from '../models/parish.model';

export namespace ParishSharedActions {
  export const types = {
    CreateParish: '[Parish] Create Parish',
    CreateParishFail: '[Parish] Create Parish Fail',
    CreateParishSuccess: '[Parish] Create Parish Success',
    LoadParishList: '[Parish] Load Parish List',
    LoadParishListFail: '[Parish] Load Parish List Fail',
    LoadParishListSuccess: '[Parish] Load Parish List Success',
    DeleteParish: '[Parish] Delete Parish',
    DeleteParishFail: '[Parish] Delete Parish Fail',
    DeleteParishSuccess: '[Parish] Delete Parish Success',
    UpdateParish: '[Parish] Update Parish',
    UpdateParishFail: '[Parish] Update Parish Fail',
    UpdateParishSuccess: '[Parish] Update Parish Success',
    SelectParish: '[Parish] Select Parish',
    SelectParishFail: '[Parish] Select Parish Fail',
    SelectParishSuccess: '[Parish] Select Parish Success',
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
  }

  export class LoadParishListSuccess implements Action {
    type = types.LoadParishListSuccess;

    constructor(public payload: Parish[]) { }
  }

  export class LoadParishListFail implements Action {
    type = types.LoadParishListFail;

    constructor(public payload: any) { }
  }

  export class DeleteParish implements Action {
    type = types.DeleteParish;

    constructor(public payload: number) { }
  }

  export class DeleteParishSuccess implements Action {
    type = types.DeleteParishSuccess;

    constructor(public payload: number) { }
  }

  export class DeleteParishFail implements Action {
    type = types.DeleteParishFail;

    constructor(public payload: any) { }
  }

  export class UpdateParish implements Action {
    type = types.UpdateParish;

    constructor(public payload: Parish) { }
  }

  export class UpdateParishSuccess implements Action {
    type = types.UpdateParishSuccess;

    constructor(public payload: Parish) { }
  }

  export class UpdateParishFail implements Action {
    type = types.UpdateParishFail;

    constructor(public payload: any) { }
  }

  export class SelectParish implements Action {
    type = types.SelectParish;

    constructor(public payload: Parish) {
    }
  }

  export class SelectParishFail implements Action {
    type = types.SelectParishFail;

    constructor(public payload: any) {
    }
  }

  export class SelectParishSuccess implements Action {
    type = types.SelectParishSuccess;

    constructor(public payload: Parish) {
    }
  }
}

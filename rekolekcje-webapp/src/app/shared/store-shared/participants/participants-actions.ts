import { Action } from '@ngrx/store';
import { Participant } from '../../models/participant.model';

export namespace ParticipantsSharedActions {
  export const types = {
    CreateParticipant: '[Participants] Create Participant',
    CreateParticipantFail: '[Participants] Create Participant Fail',
    CreateParticipantSuccess: '[Participants] Create Participant Success',
    DeleteParticipant: '[Participants] Delete Participant',
    DeleteParticipantFail: '[Participants] Delete Participant Fail',
    DeleteParticipantSuccess: '[Participants] Delete Participant Success',
    LoadParticipantsList: '[Participants] Load Participants List',
    LoadParticipantsListFail: '[Participants] Load Participants List Fail',
    LoadParticipantsListSuccess: '[Participants] Load Participants List Success',
    SelectParticipant: '[Participant] Select Participant',
    SelectParticipantFail: '[Participant] Select Participant Fail',
    SelectParticipantSuccess: '[Participant] Select Participant Success',
    UpdateParticipant: '[Participants] Update Participant',
    UpdateParticipantFail: '[Participants] Update Participant Fail',
    UpdateParticipantSuccess: '[Participants] Update Participant Success'
  };

  /**
   * Create participant actions
   */
  export class CreateParticipant implements Action {
    type = types.CreateParticipant;

    constructor(public payload: Participant) {
    }
  }

  export class CreateParticipantFail implements Action {
    type = types.CreateParticipantFail;

    constructor(public payload: any) {
    }
  }

  export class CreateParticipantSuccess implements Action {
    type = types.CreateParticipantSuccess;

    constructor(public payload: Participant) {
    }
  }

  /**
   * Delete participant actions
   */
  export class DeleteParticipant implements Action {
    type = types.DeleteParticipant;

    constructor(public payload: number) {
    }
  }

  export class DeleteParticipantFail implements Action {
    type = types.DeleteParticipantFail;

    constructor(public payload: any) {
    }
  }

  export class DeleteParticipantSuccess implements Action {
    type = types.DeleteParticipantSuccess;

    constructor(public payload: number) {
    }
  }

  /**
   * Load participants list actions
   */
  export class LoadParticipantsList implements Action {
    type = types.LoadParticipantsList;

    constructor(public payload?: any) {
    }
  }

  export class LoadParticipantsListFail implements Action {
    type = types.LoadParticipantsListFail;

    constructor(public payload: any) {
    }
  }

  export class LoadParticipantsListSuccess implements Action {
    type = types.LoadParticipantsListSuccess;

    constructor(public payload: Participant[]) {
    }
  }

  /**
   * Select participant actions
   */
  export class SelectParticipant implements Action {
    type = types.SelectParticipant;

    constructor(public payload: Participant) {
    }
  }

  export class SelectParticipantFail implements Action {
    type = types.SelectParticipantFail;

    constructor(public payload: any) {
    }
  }

  export class SelectParticipantSuccess implements Action {
    type = types.SelectParticipantSuccess;

    constructor(public payload: Participant) {
    }
  }

  /**
   * Update participant actions
   */
  export class UpdateParticipant implements Action {
    type = types.UpdateParticipant;

    constructor(public payload: Participant) {
    }
  }

  export class UpdateParticipantFail implements Action {
    type = types.UpdateParticipantFail;

    constructor(public payload: any) {
    }
  }

  export class UpdateParticipantSuccess implements Action {
    type = types.UpdateParticipantSuccess;

    constructor(public payload: Participant) {
    }
  }
}

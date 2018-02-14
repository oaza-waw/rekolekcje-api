import { Participants } from './participants/participants-reducer';
import { Parishes } from './parish/parish-reducer';

export namespace App {
  export interface State {
    participantsModule: Participants.State;
    parishModule: Parishes.State;
  }
}

export namespace AppReducer {
  export function reducer(state: App.State, action): App.State {
    switch (action.type) {
      default:
        return {...state};
    }
  }
}

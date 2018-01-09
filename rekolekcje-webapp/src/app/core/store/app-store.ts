import { Participants } from './participants/participants-reducer';

export namespace App {
  export interface State {
    participantsModule: Participants.State;
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

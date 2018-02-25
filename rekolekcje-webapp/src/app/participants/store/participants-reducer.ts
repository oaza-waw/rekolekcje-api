import { Participant } from '../models/participant.model';
import { ParticipantsSharedActions } from './participants-actions';

export namespace Participants {
  export interface State {
    participantsList: Participant[];
    participantsLoading: boolean;
    selectedParticipant: Participant;
  }

  export const initialState: Participants.State = {
    participantsList: [],
    participantsLoading: false,
    selectedParticipant: null,
  };
}

export namespace ParticipantsReducer {

  export function reducer(state: Participants.State = Participants.initialState, action): Participants.State {
    switch (action.type) {

      case ParticipantsSharedActions.types.CreateParticipantFail: {
        return {...state};
      }

      case ParticipantsSharedActions.types.CreateParticipantSuccess: {
        const list: Participant[] = [...state.participantsList];
        list.push(action.payload);
        return {...state, participantsList: list};
      }

      case ParticipantsSharedActions.types.DeleteParticipantFail: {
        return {...state};
      }

      case ParticipantsSharedActions.types.DeleteParticipantSuccess: {
        return {...state, participantsList: state.participantsList.filter((participant: Participant) =>
          participant.id !== action.payload)};
      }

      case ParticipantsSharedActions.types.LoadParticipantsListFail: {
        return {...state};
      }

      case ParticipantsSharedActions.types.LoadParticipantsListSuccess: {
        return {...state, participantsList: action.payload};
      }

      case ParticipantsSharedActions.types.SelectParticipantSuccess: {
        return {...state, selectedParticipant: action.payload};
      }

      case ParticipantsSharedActions.types.UpdateParticipantFail: {
        return {...state};
      }

      case ParticipantsSharedActions.types.UpdateParticipantSuccess: {
        return {...state, participantsList: state.participantsList.map((participant: Participant) => {
          if (participant.id === action.payload.id) {
            return action.payload;
          } else {
            return participant;
          }
        })};
      }

      default:
        return state;
    }
  }
}

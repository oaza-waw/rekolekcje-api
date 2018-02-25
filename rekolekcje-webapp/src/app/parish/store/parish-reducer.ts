import { Parish } from '../models/parish.model';
import { ParishSharedActions } from './parish-actions';

export namespace Parishes {
  export interface State {
    parishList: Parish[];
    parishesLoading: boolean;
    selectedParish: Parish;
  }

  export const initialState: Parishes.State = {
    parishList: [],
    parishesLoading: false,
    selectedParish: null,
  };
}

export namespace ParishReducer {

  export function reducer(state: Parishes.State = Parishes.initialState, action): Parishes.State {
    switch (action.type) {

      case ParishSharedActions.types.CreateParishSuccess: {
        const list: Parish[] = [...state.parishList];
        list.push(action.payload);
        return { ...state, parishList: list };
      }

      case ParishSharedActions.types.LoadParishListSuccess: {
        return { ...state, parishList: action.payload };
      }

      case ParishSharedActions.types.DeleteParishSuccess: {
        return {
          ...state,
          parishList: state.parishList.filter((parish: Parish) => parish.id !== action.payload)
        };
      }

      case ParishSharedActions.types.UpdateParishSuccess: {
        return {
          ...state,
          parishList: state.parishList.map((parish: Parish) => {
            if (parish.id === action.payload.id) {
              return action.payload;
            } else {
              return parish;
            }
          })
        };
      }

      default:
        return state;
    }
  }
}

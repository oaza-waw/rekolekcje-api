import { Parish } from '../../../shared/models/parish.model';
import { ParishSharedActions } from '../../../shared/store-shared/parish/parish-actions';

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
        return {...state, parishList: list};
      }

      case ParishSharedActions.types.LoadParishListSuccess: {
        return {...state, parishList: action.payload};
      }

      default:
        return state;
    }
  }
}

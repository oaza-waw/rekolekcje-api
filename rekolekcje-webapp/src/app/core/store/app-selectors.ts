import { createFeatureSelector, createSelector } from '@ngrx/store';
import { Participants } from './participants/participants-reducer';
import { Parishes } from './parish/parish-reducer';

export namespace AppSelectors {
  export const getParticipantsModuleState = createFeatureSelector<Participants.State>('participantsModule');
  export const getParishModuleState = createFeatureSelector<Parishes.State>('parishModule');

  /**
   * Participants selectors
   */
  export const getParticipantsList = createSelector(
    getParticipantsModuleState,
    (state: Participants.State) => state.participantsList
  );

  export const getParticipantsLoading = createSelector(
    getParticipantsModuleState,
    (state: Participants.State) => state.participantsLoading
  );

  export const getSelectedParticipant = createSelector(
    getParticipantsModuleState,
    (state: Participants.State) => state.selectedParticipant
  );

  /**
   * Parish selectors
   */
  export const getParishList = createSelector(
    getParishModuleState,
    (state: Parishes.State) => state.parishList
  );
}

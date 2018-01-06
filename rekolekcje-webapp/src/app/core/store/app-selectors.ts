import { createFeatureSelector, createSelector } from '@ngrx/store';
import { Participants } from './participants/participants-reducer';

export namespace AppSelectors {
  export const getParticipantsModuleState = createFeatureSelector<Participants.State>('participantsModule');

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
}

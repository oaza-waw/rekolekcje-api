import { environment } from './environments/environment';

export const Config = {
  endpoints: {
    participantsModule: environment.apiUrl + '/participants',
    parishModule: environment.apiUrl + '/parish'
  },

  inputDebounceTime: 250,
};

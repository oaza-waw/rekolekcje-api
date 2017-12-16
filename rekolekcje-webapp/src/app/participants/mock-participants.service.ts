import { Injectable } from '@angular/core';
import { Participant } from '../shared/model/participant.model';
import { Observable } from 'rxjs/Observable';
import { MOCK_PARTICIPANTS } from '../mock-data/mock-participants';
import { of } from 'rxjs/observable/of';

@Injectable()
export class MockParticipantsService {

  private participants: Participant[] = MOCK_PARTICIPANTS;
  private nextId;

  constructor() {
    this.nextId = this.participants.length + 1;
  }

  findAll(): Observable<Participant[]> {
    return of(this.participants);
  }

  find(id: number): Observable<Participant> {
    return of(this.participants.find(participant => participant.id === id));
  }

  add(participant: Participant): Observable<Participant> {
    let newParticipant = {
      id: this.nextId++,
      firstName: participant.firstName,
      lastName: participant.lastName,
      pesel: participant.pesel,
      address: participant.address,
      parish: participant.parish
    };
    this.participants = this.participants.concat(newParticipant);
    return of(newParticipant);
  }

  deleteOne(participantId: number): Observable<number> {
    this.participants = this.participants.filter(p => p.id != participantId);
    return of(participantId);
  }
}

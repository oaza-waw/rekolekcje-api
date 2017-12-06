import { Injectable } from '@angular/core';
import { Participant } from './participant.model';
import { Observable } from 'rxjs/Observable';
import { MOCK_PARTICIPANTS } from '../mock-data/mock-participants';
import { of } from 'rxjs/observable/of';
import { ReplaySubject } from 'rxjs/ReplaySubject';

@Injectable()
export class MockParticipantsService {

  private participantsData: Participant[] = MOCK_PARTICIPANTS;
  private nextId;
  public participants: ReplaySubject<Participant[]> = new ReplaySubject(1);

  constructor() {
    this.nextId = this.participantsData.length + 1;
  }

  findAll(): Participant[] {
    this.participants.next(this.participantsData);
    return this.participantsData;
  }

  find(id: number): Participant {
    return this.participantsData.find(participant => participant.id === id);
  }

  add(participant: Participant): Participant {
    let newParticipant = {
      id: this.nextId++,
      firstName: participant.firstName,
      lastName: participant.lastName,
      pesel: participant.pesel,
      address: participant.address,
      parish: participant.parish
    };
    this.participantsData = this.participantsData.concat(newParticipant);
    this.participants.next(this.participantsData);
    return newParticipant;
  }

  deleteOne(participantId: number): number {
    this.participantsData = this.participantsData.filter(p => p.id != participantId);
    this.participants.next(this.participantsData);
    return participantId;
  }

  // findAll(): Observable<Participant[]> {
  //   return of(this.participantsData);
  // }
  //
  // find(id: number): Observable<Participant> {
  //   return of(this.participantsData.find(participant => participant.id === id));
  // }
  //
  // add(participant: Participant): Observable<Participant> {
  //   let newParticipant = {
  //     id: this.nextId++,
  //     firstName: participant.firstName,
  //     lastName: participant.lastName,
  //     pesel: participant.pesel,
  //     address: participant.address,
  //     parish: participant.parish
  //   };
  //   this.participantsData = this.participantsData.concat(newParticipant);
  //   return of(newParticipant);
  // }
  //
  // deleteOne(participantId: number): Observable<number> {
  //   this.participantsData = this.participantsData.filter(p => p.id != participantId);
  //   return of(participantId);
  // }
}

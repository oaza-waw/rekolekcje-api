import {Injectable} from "@angular/core";
import {Participant} from "./participant.model";
import {Observable} from "rxjs/Observable";
import {MOCK_PARTICIPANTS} from "../mock-data/mock-participants";
import {of} from "rxjs/observable/of";

@Injectable()
export class MockParticipantsService {

  private participants: Participant[] = MOCK_PARTICIPANTS;

  findAll(): Observable<Participant[]> {
    return of(this.participants);
  }

  find(id: number): Observable<Participant> {
    return of(this.participants.find(participant => participant.id === id));
  }
}

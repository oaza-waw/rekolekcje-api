import {Injectable} from "@angular/core";
import {Participant} from "./participant.model";
import {Observable} from "rxjs/Observable";
import {MOCK_PARTICIPANTS} from "../mock-data/mock-participants";
import "rxjs/add/observable/of";

@Injectable()
export class MockParticipantsService {

  private participants: Participant[] = MOCK_PARTICIPANTS;

  findAll(): Observable<Participant[]> {
    return Observable.of(this.participants);
  }
}

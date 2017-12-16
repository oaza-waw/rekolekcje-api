import { Component, OnDestroy, OnInit } from '@angular/core';
import { Participant } from '../shared/model/participant.model';
import { MockParticipantsService } from './mock-participants.service';
import { Subject } from 'rxjs/Subject';

@Component({
  selector: 'participants-root',
  templateUrl: './participants.component.html'
})
export class ParticipantsComponent implements OnInit, OnDestroy {

  participants: Participant[];

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private participantsService: MockParticipantsService) {}

  ngOnInit(): void {
    this.participantsService
      .findAll()
      .takeUntil(this.ngUnsubscribe)
      .subscribe(participants => this.participants = participants );
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onAddParticipantHandler(participant: Participant): void {
    this.participantsService
      .add(participant)
      .takeUntil(this.ngUnsubscribe)
      .subscribe(newParticipant => {
        this.participants = this.participants.concat(newParticipant);
      });
  }

  onDeleteParticipantHandler(participantId: number): void {
    this.participantsService
      .deleteOne(participantId)
      .takeUntil(this.ngUnsubscribe)
      .subscribe(idOfDeleted => {
        this.participants = this.participants.filter(p => p.id != idOfDeleted);
      });
  }

  onEditParticipantHandler(participant: Participant): void {

  }
}

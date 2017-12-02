import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Participant} from '../participant.model';

@Component({
  selector: 'participants-list',
  templateUrl: './participants-list.component.html',
  styleUrls: ['./participants-list.component.css']
})
export class ParticipantsListComponent implements OnInit {
  title = 'All participants';

  @Input() participants: Participant[];

  @Output() deleteOneEvent: EventEmitter<number> = new EventEmitter();

  constructor() {
  }

  ngOnInit(): void {
  }

  deleteOne(id: number) {
    this.deleteOneEvent.emit(id);
  }
}

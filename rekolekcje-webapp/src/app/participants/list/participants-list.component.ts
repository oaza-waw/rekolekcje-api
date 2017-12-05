import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Participant} from '../participant.model';
import { Observable } from 'rxjs/Observable';
import { DialogService } from '../../dialog.service';

@Component({
  selector: 'participants-list',
  templateUrl: './participants-list.component.html',
  styleUrls: ['./participants-list.component.css']
})
export class ParticipantsListComponent implements OnInit {
  title = 'All participants';

  @Input() participants: Participant[];

  // @Output() deleteOneEvent: EventEmitter<number> = new EventEmitter();

  constructor(private dialogService: DialogService) {
  }

  ngOnInit(): void {
  }

  deleteOne(id: number) {
    console.log('wuut');
  //   this.deleteOneEvent.emit(id);
  }

  canDeactivate(): Observable<boolean> {
    return this.dialogService.confirm('Delete participant?');
  }
}

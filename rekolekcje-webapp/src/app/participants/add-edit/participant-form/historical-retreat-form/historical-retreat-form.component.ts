import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { RetreatTurn } from '../../../models/retreat-turn.model';

@Component({
  selector: 'reko-historical-retreat-form',
  templateUrl: './historical-retreat-form.component.html',
  styleUrls: ['./historical-retreat-form.component.css']
})
export class HistoricalRetreatFormComponent {

  @Input() index: number;
  @Input() retreatData: RetreatTurn;
  @Input() historicalRetreatForm: FormGroup;

  @Output() removed: EventEmitter<number> = new EventEmitter<number>();

  static buildFormConfig(retreatTurn: RetreatTurn) {
    if (retreatTurn != null) {
      return new FormGroup({
        stage: new FormControl(retreatTurn.stage ? retreatTurn.stage : ''),
        location: new FormControl(retreatTurn.location ? retreatTurn.location : ''),
        year: new FormControl(retreatTurn.year ? retreatTurn.year : ''),
      });
    } else {
      return new FormGroup({
        stage: new FormControl(''),
        location: new FormControl(''),
        year: new FormControl(''),
      });
    }
  }
}

import { Component, Input } from '@angular/core';
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

  static buildFormConfig(retreatTurn: RetreatTurn) {
    return new FormGroup({
      stage: new FormControl(''),
      location: new FormControl(''),
      year: new FormControl(''),
    });
  }
}

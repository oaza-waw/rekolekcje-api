import { Component, Input } from '@angular/core';
import { FormArray } from '@angular/forms';
import { RetreatTurn } from '../../../models/retreat-turn.model';
import { HistoricalRetreatFormComponent } from '../historical-retreat-form/historical-retreat-form.component';

@Component({
  selector: 'reko-historical-retreats-array',
  templateUrl: './historical-retreats-array.component.html',
  styleUrls: ['./historical-retreats-array.component.css']
})
export class HistoricalRetreatsArrayComponent {

  @Input() retreatsFormArray: FormArray;

  addRetreat() {
    this.retreatsFormArray.push(HistoricalRetreatFormComponent.buildFormConfig(null));
  }

  static buildFormConfig(historicalRetreats: RetreatTurn[]) {
    if (historicalRetreats != null) {
      return new FormArray(historicalRetreats.map(it => HistoricalRetreatFormComponent.buildFormConfig(it)));
    } else {
      return new FormArray([HistoricalRetreatFormComponent.buildFormConfig(null)]);
    }
  }
}

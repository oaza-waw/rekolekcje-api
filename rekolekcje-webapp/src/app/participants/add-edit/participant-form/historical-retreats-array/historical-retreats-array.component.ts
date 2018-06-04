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
    console.log('adding new...');
    this.retreatsFormArray.push(HistoricalRetreatFormComponent.buildFormConfig(null));
    console.log('array after adding: ', this.retreatsFormArray);
  }

  static buildFormConfig(historicalRetreats: RetreatTurn[]) {
    if (historicalRetreats != null) {
      console.log('is not null. size: ', historicalRetreats.length);
      return new FormArray(historicalRetreats.map(it => HistoricalRetreatFormComponent.buildFormConfig(it)));
    } else {
      console.log('is null');
      return new FormArray([HistoricalRetreatFormComponent.buildFormConfig(null)]);
    }
  }
}

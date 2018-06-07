import { Component, Input } from '@angular/core';
import { RetreatTurn } from '../../../models/retreat-turn.model';

@Component({
  selector: 'reko-historical-retreats-details',
  templateUrl: './historical-retreats-details.component.html',
  styleUrls: ['./historical-retreats-details.component.css']
})
export class HistoricalRetreatsDetailsComponent {

  @Input() retreats: RetreatTurn[];

}

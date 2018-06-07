import { Component, Input } from '@angular/core';
import { RetreatTurn } from '../../../models/retreat-turn.model';

@Component({
  selector: 'reko-historical-retreats-details-element',
  templateUrl: './historical-retreats-details-element.component.html',
  styleUrls: ['./historical-retreats-details-element.component.css']
})
export class HistoricalRetreatsDetailsElementComponent {

  @Input() retreatTurnData: RetreatTurn;
}

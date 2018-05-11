import {Component, Input} from '@angular/core';

@Component({
  selector: 'health-report',
  templateUrl: './health-report.component.html',
  styleUrls: ['./health-report.component.css']
})
export class HealthReportComponent {

  @Input() currentTreatment: string;
  @Input() medications: string;
  @Input() allergies: string;
  @Input() other: string;

  constructor(){}
}

import {Component, Input} from '@angular/core';
import {HealthReport} from "../../models/heath-report.model";

@Component({
  selector: 'health-report',
  templateUrl: './health-report.component.html',
  styleUrls: ['./health-report.component.css']
})
export class HealthReportComponent {


  @Input() healthReportData: HealthReport;

  constructor(){}
}

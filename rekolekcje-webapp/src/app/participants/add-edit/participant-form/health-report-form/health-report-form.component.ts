import { Component, Input } from '@angular/core';
import { HealthReport } from '../../../models/heath-report.model';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'health-report-form',
  templateUrl: './health-report-form.component.html',
  styleUrls: ['./health-report-form.component.css']
})
export class HealthReportFormComponent {

  @Input() public healthReportData: HealthReport;
  @Input() public healthReportForm: FormGroup;

}

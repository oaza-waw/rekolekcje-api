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

  static buildFormConfig(healthReport: HealthReport) {
    if (healthReport != null) {
      return {
        currentTreatment: [healthReport.currentTreatment ? healthReport.currentTreatment : ''],
        mentalDisorders: [healthReport.mentalDisorders ? healthReport.mentalDisorders : ''],
        medications: [healthReport.medications ? healthReport.medications : ''],
        allergies: [healthReport.allergies ? healthReport.allergies : ''],
        medicalDiet: [healthReport.medicalDiet ? healthReport.medicalDiet : ''],
        mountainWalkingContraindications: [healthReport.mountainWalkingContraindications
          ? healthReport.mountainWalkingContraindications : ''],
        seriousDiseasesAndOperations: [healthReport.seriousDiseasesAndOperations
          ? healthReport.seriousDiseasesAndOperations : ''],
        locomotiveDisease: [healthReport.locomotiveDisease ? healthReport.locomotiveDisease : ''],
        other: [healthReport.other ? healthReport.other : ''],
      }
    } else {
      return {
        currentTreatment: '',
        mentalDisorders: '',
        medications: '',
        allergies: '',
        medicalDiet: '',
        mountainWalkingContraindications: '',
        seriousDiseasesAndOperations: '',
        locomotiveDisease: '',
        other: '',
      }
    }
  }
}

import { FormGroup } from '@angular/forms';

export class HealthReport {
  constructor(
    public currentTreatment?: string,
    public medications?: string,
    public allergies?: string,
    public other?: string
  ) {}

  static mapFromForm(input: any): HealthReport {
    const healthReport: HealthReport = new HealthReport();
    healthReport.currentTreatment = input.result.healthReport.currentTreatment;
    healthReport.medications = input.result.healthReport.medications;
    healthReport.allergies = input.result.healthReport.allergies;
    healthReport.other = input.result.healthReport.other;
    return healthReport;
  }

  static parseForm(form: FormGroup): HealthReport {
    const healthReport = new HealthReport();
    healthReport.currentTreatment = form.get('healthReport.currentTreatment').value;
    healthReport.medications = form.get('healthReport.medications').value;
    healthReport.allergies = form.get('healthReport.allergies').value;
    healthReport.other = form.get('healthReport.other').value;
    return healthReport;
  }
}

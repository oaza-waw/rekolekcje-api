import { FormGroup } from '@angular/forms';

export class HealthReport {
  constructor(
    public currentTreatment?: string,
    public mentalDisorders?: string,
    public medications?: string,
    public allergies?: string,
    public medicalDiet?: string,
    public canHike?: boolean,
    public illnessHistory?: string,
    public hasMotionSickness?: boolean,
    public other?: string
  ) {}

  static mapFromForm(input: any): HealthReport {
    const healthReport: HealthReport = new HealthReport();
    healthReport.currentTreatment = input.result.healthReport.currentTreatment;
    healthReport.mentalDisorders = input.result.healthReport.mentalDisorders;
    healthReport.medications = input.result.healthReport.medications;
    healthReport.allergies = input.result.healthReport.allergies;
    healthReport.medicalDiet = input.result.healthReport.medicalDiet;
    healthReport.canHike = input.result.healthReport.canHike;
    healthReport.illnessHistory = input.result.healthReport.illnessHistory;
    healthReport.hasMotionSickness = input.result.healthReport.hasMotionSickness;
    healthReport.other = input.result.healthReport.other;
    return healthReport;
  }

  static parseForm(form: FormGroup): HealthReport {
    const healthReport = new HealthReport();
    healthReport.currentTreatment = form.get('healthReport.currentTreatment').value;
    healthReport.mentalDisorders = form.get('healthReport.mentalDisorders').value;
    healthReport.medications = form.get('healthReport.medications').value;
    healthReport.allergies = form.get('healthReport.allergies').value;
    healthReport.medicalDiet = form.get('healthReport.medicalDiet').value;
    healthReport.canHike = form.get('healthReport.canHike').value;
    healthReport.illnessHistory = form.get('healthReport.illnessHistory').value;
    healthReport.hasMotionSickness = form.get('healthReport.hasMotionSickness').value;
    healthReport.other = form.get('healthReport.other').value;
    return healthReport;
  }
}

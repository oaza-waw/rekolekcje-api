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
    console.log("map from form HealthRepoer -> " + JSON.stringify(healthReport));
    return healthReport;
  }
}

import { PersonalData } from './personal-data.model';
import { HealthReport } from './heath-report.model';
import { Experience } from './experience.model';
import { FormGroup } from '@angular/forms';

export class Participant {
  constructor(
    public id?: number,
    public personalData?: PersonalData,
    public healthReport?: HealthReport,
    public experience?: Experience
  ) { }

  static mapFromForm(input: any): Participant {
    const p: Participant = new Participant();
    p.personalData = PersonalData.mapFromForm(input);
    p.healthReport = HealthReport.mapFromForm(input);
    p.experience = Experience.mapFromForm(input);
    return p;
  }

  static parseForm(id: number, form: FormGroup): Participant {
    let participant = new Participant();
    participant.id = id;
    participant.personalData = PersonalData.parseForm(form);
    participant.healthReport = HealthReport.parseForm(form);
    participant.experience = Experience.parseForm(form);
    return participant;
  }
}

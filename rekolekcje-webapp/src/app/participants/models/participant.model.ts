import { Address } from './address.model';
import { PersonalData } from './personal-data.model';
import { HealthReport } from './heath-report.model';
import { Experience } from './experience.model';
import { FormGroup } from '@angular/forms';

export class Participant {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public pesel?: number,
    public address?: Address,
    public parishId?: number,
    public personalData?: PersonalData,
    public healthReport?: HealthReport,
    public experience?: Experience
  ) { }

  static mapFromForm(input: any): Participant {
    const p: Participant = new Participant();
    p.firstName = input.result.firstName;
    p.lastName = input.result.lastName;
    p.pesel = input.result.pesel;
    p.address = Address.mapFromForm(input);
    p.parishId = input.result.parishId;
    p.personalData = PersonalData.mapFromForm(input);
    p.healthReport = HealthReport.mapFromForm(input);
    p.experience = Experience.mapFromForm(input);
    return p;
  }

  static parseForm(id: number, form: FormGroup): Participant {
    let participant = new Participant();
    participant.id = id;
    participant.firstName = form.get('personalDataGroup.firstName').value;
    participant.lastName = form.get('personalDataGroup.lastName').value;
    participant.pesel = form.get('personalDataGroup.pesel').value;
    participant.parishId = form.get('personalDataGroup.parishId').value;
    participant.personalData = PersonalData.parseForm(form);
    participant.address = Address.parseForm(form);
    participant.healthReport = HealthReport.parseForm(form);
    participant.experience = Experience.parseForm(form);
    return participant;
  }
}

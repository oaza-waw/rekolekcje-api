import { Moment } from 'moment';
import { FormGroup } from '@angular/forms';

export class PersonalData {
  constructor(
    public fatherName?: string,
    public motherName?: string,
    public christeningPlace?: string,
    public christeningDate?: Moment,
    public emergencyContactName?: string,
    public emergencyContactNumber?: number,
  ) { }

  static mapFromForm(input: any): PersonalData {
    const personalData: PersonalData = new PersonalData();
    personalData.fatherName = input.result.personalData.fatherName;
    personalData.motherName = input.result.personalData.motherName;
    personalData.christeningPlace = input.result.personalData.christeningPlace;
    personalData.christeningDate = input.result.personalData.christeningDate;
    personalData.emergencyContactName = input.result.personalData.emergencyContactName;
    personalData.emergencyContactNumber = input.result.personalData.emergencyContactNumber;
    return personalData;
  }

  static parseForm(form: FormGroup): PersonalData {
    const personalData = new PersonalData();
    personalData.christeningDate = form.get('personalDataGroup.christeningDate').value;
    personalData.christeningPlace = form.get('personalDataGroup.christeningPlace').value;
    personalData.fatherName = form.get('personalDataGroup.fatherName').value;
    personalData.motherName = form.get('personalDataGroup.motherName').value;
    personalData.emergencyContactName = form.get('personalDataGroup.emergencyContactName').value;
    personalData.emergencyContactNumber = form.get('personalDataGroup.emergencyContactNumber').value;
    return personalData;
  }
}

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
    personalData.christeningDate = form.get('christeningDate').value;
    personalData.christeningPlace = form.get('christeningPlace').value;
    personalData.fatherName = form.get('fatherName').value;
    personalData.motherName = form.get('motherName').value;
    personalData.emergencyContactName = form.get('emergencyContactName').value;
    personalData.emergencyContactNumber = form.get('emergencyContactNumber').value;
    return personalData;
  }
}

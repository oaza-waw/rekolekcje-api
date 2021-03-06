import { Moment } from 'moment';
import { FormGroup } from '@angular/forms';
import { Address } from './address.model';

export class PersonalData {
  constructor(
    public firstName?: string,
    public lastName?: string,
    public pesel?: number,
    public phoneNumber?: number,
    public email?: string,
    public address?: Address,
    public parishId?: number,
    public fatherName?: string,
    public motherName?: string,
    public christeningPlace?: string,
    public christeningDate?: Moment,
    public emergencyContactName?: string,
    public emergencyContactNumber?: number,
    public schoolYear?: string,
    public nameDay?: string,
    public communityName?: string,
  ) { }

  static mapFromForm(input: any): PersonalData {
    const personalData: PersonalData = new PersonalData();
    personalData.firstName = input.result.personalData.firstName;
    personalData.lastName = input.result.personalData.lastName;
    personalData.pesel = input.result.personalData.pesel;
    personalData.phoneNumber = input.result.personalData.phoneNumber;
    personalData.email = input.result.personalData.email;
    personalData.address = Address.mapFromForm(input);
    personalData.parishId = input.result.personalData.parishId;
    personalData.fatherName = input.result.personalData.fatherName;
    personalData.motherName = input.result.personalData.motherName;
    personalData.christeningPlace = input.result.personalData.christeningPlace;
    personalData.christeningDate = input.result.personalData.christeningDate;
    personalData.emergencyContactName = input.result.personalData.emergencyContactName;
    personalData.emergencyContactNumber = input.result.personalData.emergencyContactNumber;
    personalData.schoolYear = input.result.personalData.schoolYear;
    personalData.nameDay = input.result.personalData.nameDay;
    personalData.communityName = input.result.personalData.communityName;
    return personalData;
  }

  static parseForm(form: FormGroup): PersonalData {
    const personalData = new PersonalData();
    personalData.firstName = form.get('personalDataGroup.firstName').value;
    personalData.lastName = form.get('personalDataGroup.lastName').value;
    personalData.pesel = form.get('personalDataGroup.pesel').value;
    personalData.email = form.get('personalDataGroup.email').value;
    personalData.phoneNumber = form.get('personalDataGroup.phoneNumber').value;
    personalData.parishId = form.get('personalDataGroup.parishId').value;
    personalData.address = Address.parseForm(form);
    personalData.christeningDate = form.get('personalDataGroup.christeningDate').value;
    personalData.christeningPlace = form.get('personalDataGroup.christeningPlace').value;
    personalData.fatherName = form.get('personalDataGroup.fatherName').value;
    personalData.motherName = form.get('personalDataGroup.motherName').value;
    personalData.emergencyContactName = form.get('personalDataGroup.emergencyContactName').value;
    personalData.emergencyContactNumber = form.get('personalDataGroup.emergencyContactNumber').value;
    personalData.schoolYear = form.get('personalDataGroup.schoolYear').value;
    personalData.nameDay = form.get('personalDataGroup.nameDay').value;
    personalData.communityName = form.get('personalDataGroup.communityName').value;
    return personalData;
  }
}

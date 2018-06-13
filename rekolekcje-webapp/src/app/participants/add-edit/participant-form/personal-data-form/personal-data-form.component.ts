import { Component, Input } from '@angular/core';
import { PersonalData } from '../../../models/personal-data.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Parish } from '../../../../parish/models/parish.model';
import { AddressFormComponent } from '../address-form/address-form.component';

@Component({
  selector: 'reko-personal-data-form',
  templateUrl: './personal-data-form.component.html',
  styleUrls: ['./personal-data-form.component.css']
})
export class PersonalDataFormComponent {

  @Input() personalData: PersonalData;
  @Input() group: FormGroup;
  @Input() parishes: Parish[];

  static buildFormConfig(personalData: PersonalData) {
    if (personalData != null) {
      return new FormGroup({
        firstName: new FormControl(personalData.firstName ? personalData.firstName : '', Validators.required),
        lastName: new FormControl(personalData.lastName ? personalData.lastName : '', Validators.required),
        pesel: new FormControl(personalData.pesel ? personalData.pesel : '', Validators.required),
        phoneNumber: new FormControl(personalData.phoneNumber ? personalData.phoneNumber : ''),
        email: new FormControl(personalData.email ? personalData.email : ''),
        address: AddressFormComponent.buildFormConfig(personalData.address),
        parishId: new FormControl(personalData.parishId ? personalData.parishId : ''),
        christeningDate: new FormControl(personalData.christeningDate ? personalData.christeningDate : null),
        christeningPlace: new FormControl(personalData.christeningPlace ? personalData.christeningPlace : ''),
        fatherName: new FormControl(personalData.fatherName ? personalData.fatherName : ''),
        motherName: new FormControl(personalData.motherName ? personalData.motherName : ''),
        emergencyContactName: new FormControl(personalData.emergencyContactName ? personalData.emergencyContactName : ''),
        emergencyContactNumber: new FormControl(personalData.emergencyContactNumber ? personalData.emergencyContactNumber : ''),
        nameDay: new FormControl(personalData.nameDay ? personalData.nameDay : ''),
        schoolYear: new FormControl(personalData.schoolYear ? personalData.schoolYear : ''),
        communityName: new FormControl(personalData.communityName ? personalData.communityName : ''),
      })
    } else {
      return new FormGroup({
        firstName: new FormControl('', Validators.required),
        lastName: new FormControl('', Validators.required),
        pesel: new FormControl('', Validators.required),
        phoneNumber: new FormControl(''),
        email: new FormControl(''),
        address: AddressFormComponent.buildFormConfig(null),
        parishId: new FormControl(''),
        christeningDate: new FormControl(null),
        christeningPlace: new FormControl(''),
        fatherName: new FormControl(''),
        motherName: new FormControl(''),
        emergencyContactName: new FormControl(''),
        emergencyContactNumber: new FormControl(''),
        nameDay: new FormControl(''),
        schoolYear: new FormControl(''),
        communityName: new FormControl(''),
      })
    }
  }
}

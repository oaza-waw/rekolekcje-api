import { Component, Input } from '@angular/core';
import { PersonalData } from '../../../models/personal-data.model';
import { Address } from '../../../models/address.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Parish } from '../../../../parish/models/parish.model';
import { AddressFormComponent } from '../address-form/address-form.component';

@Component({
  selector: 'reko-personal-data-form',
  templateUrl: './personal-data-form.component.html',
  styleUrls: ['./personal-data-form.component.css']
})
export class PersonalDataFormComponent {

  @Input() firstName: string;
  @Input() lastName: string;
  @Input() pesel: string;
  @Input() parishId: string;
  @Input() address: Address;
  @Input() personalData: PersonalData;
  @Input() group: FormGroup;
  @Input() parishes: Parish[];

  static buildFormConfig(firstName: string, lastName: string, pesel: string, parishId: string, personalData: PersonalData, address: Address) {
    if (personalData != null) {
      return new FormGroup({
        firstName: new FormControl(firstName ? firstName : '', Validators.required),
        lastName: new FormControl(lastName ? lastName : '', Validators.required),
        pesel: new FormControl(pesel ? pesel : '', Validators.required),
        address: AddressFormComponent.buildFormConfig(address),
        parishId: new FormControl(parishId ? parishId : ''),
        christeningDate: new FormControl(personalData.christeningDate ? personalData.christeningDate : null),
        christeningPlace: new FormControl(personalData.christeningPlace ? personalData.christeningPlace : ''),
        fatherName: new FormControl(personalData.fatherName ? personalData.fatherName : ''),
        motherName: new FormControl(personalData.motherName ? personalData.motherName : ''),
        emergencyContactName: new FormControl(personalData.emergencyContactName ? personalData.emergencyContactName : ''),
        emergencyContactNumber: new FormControl(personalData.emergencyContactNumber ? personalData.emergencyContactNumber : ''),
      })
    } else {
      return new FormGroup({
        firstName: new FormControl(firstName ? firstName : '', Validators.required),
        lastName: new FormControl(lastName ? lastName : '', Validators.required),
        pesel: new FormControl(pesel ? pesel : '', Validators.required),
        address: AddressFormComponent.buildFormConfig(address),
        parishId: new FormControl(parishId ? parishId : ''),
        christeningDate: new FormControl(null),
        christeningPlace: new FormControl(''),
        fatherName: new FormControl(''),
        motherName: new FormControl(''),
        emergencyContactName: new FormControl(''),
        emergencyContactNumber: new FormControl(''),
      })
    }
  }
}

import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Address } from '../../../models/address.model';

@Component({
  selector: 'address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.css'],
})
export class AddressFormComponent {

  @Input() public addressData: Address;
  @Input() public addressForm: FormGroup;

  static buildFormConfig(address: Address) {
    if (address != null) {
      return new FormGroup({
        streetName: new FormControl(address.streetName ? address.streetName : ''),
        streetNumber: new FormControl(address.streetNumber ? address.streetNumber : ''),
        flatNumber: new FormControl(address.flatNumber ? address.flatNumber : ''),
        postalCode: new FormControl(address.postalCode ? address.postalCode : ''),
        city: new FormControl(address.city ? address.city : ''),
      })
    } else {
      return new FormGroup({
        streetName: new FormControl( ''),
        streetNumber: new FormControl(''),
        flatNumber: new FormControl(''),
        postalCode: new FormControl(''),
        city: new FormControl(''),
      })
    }
  }
}

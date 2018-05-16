import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
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
      return {
        streetName: [address.streetName ? address.streetName : ''],
        streetNumber: [address.streetNumber ? address.streetNumber : ''],
        flatNumber: [address.flatNumber ? address.flatNumber : ''],
        postalCode: [address.postalCode ? address.postalCode : ''],
        city: [address.city ? address.city : ''],
      }
    } else {
      return { streetName: '', streetNumber: '', flatNumber: '', postalCode: '', city: '' }
    }
  }
}

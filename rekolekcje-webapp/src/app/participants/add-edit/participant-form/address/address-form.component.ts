import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Address } from '../../../models/address.model';

@Component({
  selector: 'address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.css'],
})
export class AddressFormComponent {

  @Input()
  public addressData: Address;

  @Input()
  public addressForm: FormGroup;
}

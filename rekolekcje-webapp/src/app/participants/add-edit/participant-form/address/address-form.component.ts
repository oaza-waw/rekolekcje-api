import {Component, Input, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Address} from "../../../models/participant.model";

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

  // constructor(private fb: FormBuilder) { }

  // ngOnInit(): void {
  //   this.addressForm = this.fb.group({
  //     streetName: [this.addressData.streetName ? this.addressData.streetName : '', Validators.required],
  //     streetNumber: [this.addressData.streetNumber ? this.addressData.streetNumber : ''],
  //     flatNumber: [this.addressData.flatNumber ? this.addressData.flatNumber : ''],
  //     postalCode: [this.addressData.postalCode ? this.addressData.postalCode : ''],
  //     city: [this.addressData.city ? this.addressData.city : ''],
  //   })
  // }
}

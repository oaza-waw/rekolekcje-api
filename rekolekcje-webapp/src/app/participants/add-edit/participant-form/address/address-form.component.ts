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
  //     street: [this.addressData.street ? this.addressData.street : '', Validators.required],
  //     number: [this.addressData.number ? this.addressData.number : ''],
  //     flat: [this.addressData.flat ? this.addressData.flat : ''],
  //     code: [this.addressData.code ? this.addressData.code : ''],
  //     city: [this.addressData.city ? this.addressData.city : ''],
  //   })
  // }
}

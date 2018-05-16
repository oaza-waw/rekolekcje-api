import { FormGroup } from '@angular/forms';

export class Address {
  constructor(
    public streetName?: string,
    public streetNumber?: number,
    public flatNumber?: number,
    public postalCode?: string,
    public city?: string,
  ) { }

  static mapFromForm(input: any): Address {
    const address = new Address();
    address.streetName = input.result.address.streetName;
    address.streetNumber = input.result.address.streetNumber;
    address.flatNumber = input.result.address.flatNumber;
    address.postalCode = input.result.address.postalCode;
    address.city = input.result.address.city;
    return address;
  }

  static parseForm(form: FormGroup): Address {
    const address = new Address();
    address.streetName = form.get('personalDataGroup.address.streetName').value;
    address.streetNumber = form.get('personalDataGroup.address.streetNumber').value;
    address.flatNumber = form.get('personalDataGroup.address.flatNumber').value;
    address.postalCode = form.get('personalDataGroup.address.postalCode').value;
    address.city = form.get('personalDataGroup.address.city').value;
    return address;
  }
}

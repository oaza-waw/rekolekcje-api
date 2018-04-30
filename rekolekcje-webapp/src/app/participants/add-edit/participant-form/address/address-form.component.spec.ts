import { AddressFormComponent } from './address-form.component';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { participantsTestingModule } from '../../../participants-testing.module';
import { FormControl, FormGroup } from '@angular/forms';

describe('AddressFormComponent', () => {
  let component: AddressFormComponent;
  let fixture: ComponentFixture<AddressFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(participantsTestingModule).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddressFormComponent);
    component = fixture.componentInstance;
    component.addressData = {};
    component.addressForm = new FormGroup({
      streetName: new FormControl(''),
      streetNumber: new FormControl(),
      flatNumber:new FormControl(),
      postalCode: new FormControl(),
      city: new FormControl(),
    });
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

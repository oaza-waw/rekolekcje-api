import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import {Address, Participant, PersonalData} from '../../models/participant.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Parish } from '../../../parish/models/parish.model';
import { AppSelectors } from '../../../core/store/app-selectors';
import { Subject } from 'rxjs/Subject';
import { Parishes } from '../../../parish/store/parish-reducer';
import { Store } from '@ngrx/store';
import { Moment } from 'moment';

@Component({
  selector: 'participiant-form',
  templateUrl: './participant-form.component.html',
  styleUrls: ['./participant-form.component.css'],
})
export class ParticipantFormComponent implements OnInit, OnDestroy {

  @Input() christeningDate: Moment;
  @Input() christeningPlace: string;
  @Input() closeRelativeName: string;
  @Input() closeRelativeNumber: number;
  @Input() fatherName: string;
  @Input() firstName: string;
  @Input() id: number;
  @Input() lastName: string;
  @Input() motherName: string;
  @Input() pesel: string;
  @Input() parishId: string;
  @Input() street: string;
  @Input() number: number;
  @Input() flat: number;
  @Input() code: string;
  @Input() city: string;

  parishes: Parish[];

  @Output()
  formOutput: EventEmitter<Participant> = new EventEmitter<Participant>();

  form: FormGroup;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private fb: FormBuilder, private store: Store<Parishes.State>) { }

  ngOnInit(): void {
    this.store.select(AppSelectors.getParishList)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((parishes: Parish[]) => this.parishes = parishes);
    this.form = this.fb.group({
      firstName: [this.firstName ? this.firstName : '', Validators.required],
      lastName: [this.lastName ? this.lastName : '', Validators.required],
      pesel: [this.pesel ? this.pesel : '', Validators.required],
      street: [this.street ? this.street : '', Validators.required],
      number: [this.number ? this.number : ''],
      flat: [this.flat ? this.flat : ''],
      code: [this.code ? this.code : ''],
      city: [this.city ? this.city : ''],
      parishId: [this.parishId ? this.parishId : '', Validators.required],
      christeningDate: [this.christeningDate ? this.christeningDate : null],
      christeningPlace: [this.christeningPlace ? this.christeningPlace : ''],
      fatherName: [this.fatherName ? this.fatherName : ''],
      motherName: [this.motherName ? this.motherName : ''],
      closeRelativeName: [this.closeRelativeName ? this.closeRelativeName : ''],
      closeRelativeNumber: [this.closeRelativeNumber ? this.closeRelativeNumber : null]
    });
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  cancel(): void {
    this.formOutput.emit();
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAsDirty();
      return;
    }

    const participant = new Participant();
    participant.id = this.id;
    participant.firstName = this.form.get('firstName').value;
    participant.lastName = this.form.get('lastName').value;
    participant.pesel = this.form.get('pesel').value;
    participant.parishId = this.form.get('parishId').value;
    const personalData = new PersonalData();
    personalData.christeningDate = this.form.get('christeningDate').value;
    personalData.christeningPlace = this.form.get('christeningPlace').value;
    personalData.fatherName = this.form.get('fatherName').value;
    personalData.motherName = this.form.get('motherName').value;
    personalData.closeRelativeName = this.form.get('closeRelativeName').value;
    personalData.closeRelativeNumber = this.form.get('closeRelativeNumber').value;
    participant.personalData = personalData;
    const address = new Address();
    address.street = this.form.get('street').value;
    address.number = this.form.get('number').value;
    address.flat = this.form.get('flat').value;
    address.code = this.form.get('code').value;
    address.city = this.form.get('city').value;
    participant.address = address;
    this.formOutput.emit(participant);
  }
}

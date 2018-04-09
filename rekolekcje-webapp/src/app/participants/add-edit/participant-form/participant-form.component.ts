import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Participant } from '../../models/participant.model';
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

  @Input() address: string;
  @Input() christeningDate: Moment;
  @Input() christeningPlace: string;
  @Input() fatherName: string;
  @Input() firstName: string;
  @Input() id: number;
  @Input() lastName: string;
  @Input() motherName: string;
  @Input() pesel: string;
  @Input() parishId: string;

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
      address: [this.address ? this.address : '', Validators.required],
      parishId: [this.parishId ? this.parishId : '', Validators.required],
      christeningDate: [this.christeningDate ? this.christeningDate : null],
      christeningPlace: [this.christeningPlace ? this.christeningPlace : ''],
      fatherName: [this.fatherName ? this.fatherName : ''],
      motherName: [this.motherName ? this.motherName : ''],
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
    participant.address = this.form.get('address').value;
    participant.parishId = this.form.get('parishId').value;
    participant.christeningDate = this.form.get('christeningDate').value;
    participant.christeningPlace = this.form.get('christeningPlace').value;
    participant.fatherName = this.form.get('fatherName').value;
    participant.motherName = this.form.get('motherName').value;
    this.formOutput.emit(participant);
  }
}

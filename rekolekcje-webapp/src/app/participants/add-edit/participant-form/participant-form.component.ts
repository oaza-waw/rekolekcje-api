import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Participant } from '../../../shared/models/participant.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Parish } from '../../../shared/models/parish.model';
import { AppSelectors } from '../../../core/store/app-selectors';
import { Subject } from 'rxjs/Subject';
import { Parishes } from '../../../core/store/parish/parish-reducer';
import { Store } from '@ngrx/store';

@Component({
  selector: 'participiant-form',
  templateUrl: './participant-form.component.html',
  styleUrls: ['./participant-form.component.css']
})
export class ParticipantFormComponent implements OnInit, OnDestroy {

  @Input()
  firstName: string;
  @Input()
  id: number;
  @Input()
  lastName: string;
  @Input()
  pesel: string;
  @Input()
  address: string;
  @Input()
  parishId: string;

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
      parishId: [this.parishId ? this.parishId : '', Validators.required]
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
    this.formOutput.emit(participant);
  }
}

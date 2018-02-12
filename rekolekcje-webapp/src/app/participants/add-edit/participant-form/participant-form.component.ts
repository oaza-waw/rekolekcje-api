import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Participant } from '../../../shared/models/participant.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'participiant-form',
  templateUrl: './participant-form.component.html',
  styleUrls: ['./participant-form.component.css']
})
export class ParticipantFormComponent implements OnInit {

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
  parish: string;

  @Output()
  formOutput: EventEmitter<Participant> = new EventEmitter<Participant>();

  form: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      firstName: [this.firstName ? this.firstName : '', Validators.required],
      lastName: [this.lastName ? this.lastName : '', Validators.required],
      pesel: [this.pesel ? this.pesel : '', Validators.required],
      address: [this.address ? this.address : '', Validators.required],
      parish: [this.parish ? this.parish : '', Validators.required]
    });
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
    participant.parish = this.form.get('parish').value;
    this.formOutput.emit(participant);
  }
}

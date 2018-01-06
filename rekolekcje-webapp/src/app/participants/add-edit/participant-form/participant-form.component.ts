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
  lastName: string;
  @Input()
  pesel: string;
  @Input()
  address: string;
  @Input()
  parish: string;

  @Output()
  formOutput: EventEmitter<Participant> = new EventEmitter<Participant>();

  participantForm: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.participantForm = this.fb.group({
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
    if (this.participantForm.invalid) {
      this.participantForm.markAsDirty();
      return;
    }

    const participant = new Participant();
    participant.firstName = this.participantForm.get('firstName').value;
    participant.lastName = this.participantForm.get('lastName').value;
    participant.pesel = this.participantForm.get('pesel').value;
    participant.address = this.participantForm.get('address').value;
    participant.parish = this.participantForm.get('parish').value;
    this.formOutput.emit(participant);
  }
}

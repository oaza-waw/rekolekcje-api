import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Parish } from '../../../shared/models/parish.model';

@Component({
  selector: 'parish-form',
  templateUrl: './parish-form.component.html',
  styleUrls: ['./parish-form.component.css']
})
export class ParishFormComponent implements OnInit {

  @Input()
  address: string;
  @Input()
  id: number;
  @Input()
  name: string;

  @Output()
  formOutput: EventEmitter<Parish> = new EventEmitter<Parish>();

  parishForm: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.parishForm = this.fb.group({
      name: [this.name ? this.name : '', Validators.required],
      address: [this.address ? this.address : '', Validators.required],
    });
  }

  cancel(): void {
    this.formOutput.emit();
  }

  submit() : void {
    if (this.parishForm.invalid) {
      this.parishForm.markAsDirty();
      return;
    }

    const parish = new Parish();
    parish.id = this.id;
    parish.name = this.parishForm.get('name').value;
    parish.address = this.parishForm.get('address').value;

    this.formOutput.emit(parish);
  }
}

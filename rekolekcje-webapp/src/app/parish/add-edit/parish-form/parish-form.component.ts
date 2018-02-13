import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Parish } from '../../../shared/models/parish.model';

@Component({
  selector: 'parish-form',
  templateUrl: './parish-form.component.html',
  styleUrls: ['./parish-form.component.css']
})
export class ParishFormComponent implements OnInit {

  @Input() id: number;
  @Input() name: string;
  @Input() address: string;

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
    parish.name = this.name;
    parish.address = this.address;
    this.formOutput.emit(parish);
  }
}

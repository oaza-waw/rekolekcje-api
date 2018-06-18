import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CurrentApplication } from '../../../models/current-application.model';

@Component({
  selector: 'reko-current-application-form',
  templateUrl: './current-application-form.component.html',
  styleUrls: ['./current-application-form.component.css']
})
export class CurrentApplicationFormComponent {

  @Input() currentApplicationForm: FormGroup;
  @Input() currentApplicationData: CurrentApplication;

  static buildFormConfig(currentApplication: CurrentApplication) {
    if (currentApplication != null) {
      return new FormGroup({
        stage: new FormControl(currentApplication.stage ? currentApplication.stage : ''),
        turn: new FormControl(currentApplication.turn ? currentApplication.turn : ''),
      });
    } else {
      return new FormGroup({
        stage: new FormControl(''),
        turn: new FormControl(''),
      });
    }
  }
}

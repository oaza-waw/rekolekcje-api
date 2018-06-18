import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Participant } from '../../models/participant.model';
import { Parish } from '../../../parish/models/parish.model';
import { Address } from '../../models/address.model';
import { PersonalData } from '../../models/personal-data.model';
import { HealthReport } from '../../models/heath-report.model';
import { Experience } from '../../models/experience.model';
import { CurrentApplication } from '../../models/current-application.model';

@Component({
  selector: 'add-edit-dialog',
  templateUrl: './add-edit-dialog.component.html',
  styleUrls: ['./add-edit-dialog.component.css']
})
export class ParticipantAddEditDialog implements OnInit {

  dialogTitle: string;
  id: number;
  parishes: Parish[];
  personalData: PersonalData;
  healthReport: HealthReport;
  experience: Experience;
  currentApplication: CurrentApplication;

  constructor(public dialogRef: MatDialogRef<ParticipantAddEditDialog>, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.dialogTitle = data.dialogTitle;
    data.parishes ? this.parishes = data.parishes : this.parishes = [];
    this.personalData = data.personalData ? data.personalData : new PersonalData();
    this.healthReport = data.healthReport;
    this.experience = data.experience;
    this.currentApplication = data.currentApplication;
  }

  // @TODO move address in some fields (to better search functionality in the future)
  ngOnInit(): void {
  }

  submit(event: Participant): void {
    if (event) {
      event.id = this.id;
      this.dialogRef.close({ result: event });
    } else {
      this.dialogRef.close();
    }
  }
}

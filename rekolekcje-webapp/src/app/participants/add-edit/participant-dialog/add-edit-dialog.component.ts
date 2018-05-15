import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Participant } from '../../models/participant.model';
import { Parish } from '../../../parish/models/parish.model';
import { Address } from '../../models/address.model';
import { PersonalData } from '../../models/personal-data.model';
import { HealthReport } from '../../models/heath-report.model';
import { Experience } from '../../models/experience.model';

@Component({
  selector: 'add-edit-dialog',
  templateUrl: './add-edit-dialog.component.html',
  styleUrls: ['./add-edit-dialog.component.css']
})
export class ParticipantAddEditDialog implements OnInit {

  dialogTitle: string;
  firstName: string;
  id: number;
  lastName: string;
  parishId: string;
  pesel: string;
  parishes: Parish[];
  personalData: PersonalData;
  address: Address;
  healthReport: HealthReport;
  experience: Experience;

  constructor(public dialogRef: MatDialogRef<ParticipantAddEditDialog>, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.dialogTitle = data.dialogTitle;
    data.firstName ? this.firstName = data.firstName : this.firstName = '';
    data.lastName ? this.lastName = data.lastName : this.lastName = '';
    data.parishId ? this.parishId = data.parishId : this.parishId = '';
    data.pesel ? this.pesel = data.pesel : this.pesel = '';
    data.parishes ? this.parishes = data.parishes : this.parishes = [];
    this.personalData = data.personalData;
    this.address = data.address;
    this.healthReport = data.healthReport;
    this.experience = data.experience;
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

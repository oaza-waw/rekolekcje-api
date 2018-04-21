import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Participant } from '../../models/participant.model';
import { Parish } from '../../../parish/models/parish.model';
import { Moment } from 'moment';

@Component({
  selector: 'add-edit-dialog',
  templateUrl: './add-edit-dialog.component.html',
  styleUrls: ['./add-edit-dialog.component.css']
})
export class ParticipantAddEditDialog implements OnInit {

  address: string;
  dialogTitle: string;
  firstName: string;
  id: number;
  lastName: string;
  parishId: string;
  pesel: string;
  parishes: Parish[];
  christeningDate: Moment;
  christeningPlace: string;
  fatherName: string;
  motherName: string;
  currentTreatment: string;
  medications: string;
  allergies: string;
  other: string;


  constructor(public dialogRef: MatDialogRef<ParticipantAddEditDialog>, @Inject(MAT_DIALOG_DATA) public data: any) {
    data.address ? this.address = data.address : this.address = '';
    this.dialogTitle = data.dialogTitle;
    data.firstName ? this.firstName = data.firstName : this.firstName = '';
    data.lastName ? this.lastName = data.lastName : this.lastName = '';
    data.parishId ? this.parishId = data.parishId : this.parishId = '';
    data.pesel ? this.pesel = data.pesel : this.pesel = '';
    data.parishes ? this.parishes = data.parishes : this.parishes = [];
    data.christeningDate ? this.christeningDate = data.christeningDate : this.christeningDate = null;
    data.christeningPlace ? this.christeningPlace = data.christeningPlace : this.christeningPlace = '';
    data.fatherName ? this.fatherName = data.fatherName : this.fatherName = '';
    data.motherName ? this.motherName = data.motherName : this.motherName = '';
    data.currentTreatment ? this.currentTreatment = data.currentTreatment : this.currentTreatment;
    data.medications ? this.medications = data.medications : this.medications;
    data.allergies ? this.allergies = data.allergies : this.allergies;
    data.other ? this.other = data.other : this.other;
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

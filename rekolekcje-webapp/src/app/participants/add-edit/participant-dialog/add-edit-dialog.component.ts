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
  closeRelativeName: string;
  closeRelativeNumber: number;
  street: string;
  number: number;
  flat: number;
  code: string;
  city: string;

  constructor(public dialogRef: MatDialogRef<ParticipantAddEditDialog>, @Inject(MAT_DIALOG_DATA) public data: any) {
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
    data.closeRelativeName ? this.closeRelativeName = data.closeRelativeName : '';
    this.closeRelativeNumber = data.closeRelativeNumber ? data.closeRelativeNumber : null;
    this.street = data.street ? data.street : '';
    this.number = data.number ? data.number : null;
    this.code = data.code ? data.code : '';
    this.flat = data.flat ? data.flat : null;
    this.city = data.city ? data.city : '';
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

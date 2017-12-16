import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Participant } from '../../../shared/model/participant.model';

@Component({
  selector: 'add-edit-dialog',
  templateUrl: './add-edit-dialog.component.html',
  styleUrls: ['./add-edit-dialog.component.css']
})
export class ParticipantAddEditDialog implements OnInit {

  address: string;
  dialogTitle: string;
  firstName: string;
  lastName: string;
  parish: string;
  pesel: string;

  constructor(public dialogRef: MatDialogRef<ParticipantAddEditDialog>, @Inject(MAT_DIALOG_DATA) public data: any) {
    data.address ? this.address = data.address : this.address = '';
    this.dialogTitle = data.dialogTitle;
    data.firstName ? this.firstName = data.firstName : this.firstName = '';
    data.lastName ? this.lastName = data.lastName : this.lastName = '';
    data.parish ? this.parish = data.parish : this.parish = '';
    data.pesel ? this.pesel = data.pesel : this.pesel = '';
  }

  // @TODO move address in some fields (to better search functionality in the future)
  ngOnInit(): void {
  }

  submit(event: Participant): void {
    if (event) {
      this.dialogRef.close({ result: event });
    } else {
      this.dialogRef.close();
    }
  }
}

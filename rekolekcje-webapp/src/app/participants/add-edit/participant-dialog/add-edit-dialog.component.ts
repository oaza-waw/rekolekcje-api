import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Participant } from '../../../shared/models/participant.model';
import { Parish } from '../../../shared/models/parish.model';

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

  constructor(public dialogRef: MatDialogRef<ParticipantAddEditDialog>, @Inject(MAT_DIALOG_DATA) public data: any) {
    data.address ? this.address = data.address : this.address = '';
    this.dialogTitle = data.dialogTitle;
    data.firstName ? this.firstName = data.firstName : this.firstName = '';
    data.lastName ? this.lastName = data.lastName : this.lastName = '';
    data.parishId ? this.parishId = data.parishId : this.parishId = '';
    data.pesel ? this.pesel = data.pesel : this.pesel = '';
    data.parishes ? this.parishes = data.parishes : this.parishes = [];
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

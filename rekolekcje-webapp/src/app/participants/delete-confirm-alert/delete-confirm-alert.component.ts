import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'delete-confirm-alert',
  templateUrl: './delete-confirm-alert.component.html',
  styleUrls: ['./delete-confirm-alert.component.css']
})
export class DeleteConfirmAlertDialog {

  confirmMessage: string;

  constructor(public dialogRef: MatDialogRef<DeleteConfirmAlertDialog>, @Inject(MAT_DIALOG_DATA) public data) {
    this.confirmMessage = data.confirmMessage;
  }
}

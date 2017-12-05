import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'delete-confirm-alert',
  templateUrl: './participants-delete-confirm-alert.component.html',
  styleUrls: ['./participants-delete-confirm-alert.component.css']
})
export class ParticipantsDeleteConfirmAlertComponent {

  constructor(public dialogRef: MatDialogRef<ParticipantsDeleteConfirmAlertComponent>) {
  }

  public confirmMessage: string;
}

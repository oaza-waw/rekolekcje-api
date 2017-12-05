import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Participant } from '../participant.model';
import { MatDialog, MatDialogRef } from '@angular/material';
import { ParticipantsDeleteConfirmAlertComponent } from '../delete-confirm-alert/participants-delete-confirm-alert.component';

@Component({
  selector: 'participants-list',
  templateUrl: './participants-list.component.html',
  styleUrls: ['./participants-list.component.css']
})
export class ParticipantsListComponent implements OnInit {
  title = 'All participants';
  confirmDeleteAlertRef: MatDialogRef<ParticipantsDeleteConfirmAlertComponent>;

  @Input() participants: Participant[];

  @Output() deleteOneEvent: EventEmitter<number> = new EventEmitter();

  constructor(public confirmDeleteAlert: MatDialog) {
  }

  ngOnInit(): void {
  }

  deleteOne(id: number) {
    this.deleteOneEvent.emit(id);
  }

  openConfirmDeleteAlert(id: number) {
    this.confirmDeleteAlertRef = this.confirmDeleteAlert
      .open(ParticipantsDeleteConfirmAlertComponent, {
        width: '400px',
        disableClose: false,
        role: 'alertdialog',
      });
    this.confirmDeleteAlertRef.componentInstance.confirmMessage = 'Are you sure you want to delete?';
    this.confirmDeleteAlertRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteOneEvent.emit(id);
      }
      this.confirmDeleteAlertRef = null;
    });
  }
}

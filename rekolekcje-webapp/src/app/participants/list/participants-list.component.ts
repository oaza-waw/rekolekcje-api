import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Participant } from '../participant.model';
import { MatDialog, MatDialogRef } from '@angular/material';
import { ParticipantsDeleteConfirmAlertComponent } from '../delete-confirm-alert/participants-delete-confirm-alert.component';
import { MockParticipantsService } from '../mock-participants.service';

@Component({
  selector: 'participants-list',
  templateUrl: './participants-list.component.html',
  styleUrls: ['./participants-list.component.css']
})
export class ParticipantsListComponent implements OnInit {
  title = 'All participants';
  confirmDeleteAlertRef: MatDialogRef<ParticipantsDeleteConfirmAlertComponent>;

  participants: Participant[];

  constructor(public confirmDeleteAlert: MatDialog,
              private participantsService: MockParticipantsService) {
  }

  ngOnInit(): void {
    this.participants = this.participantsService.findAll();
    this.participantsService.participants
      .subscribe(allParticipants => this.participants = allParticipants);
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
        this.participantsService.deleteOne(id);
      }
      this.confirmDeleteAlertRef = null;
    });
  }
}

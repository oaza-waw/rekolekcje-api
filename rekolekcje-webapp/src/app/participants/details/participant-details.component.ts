import { Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Participant } from '../participant.model';
import { MockParticipantsService } from '../mock-participants.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { DeleteConfirmAlertDialog } from '../delete-confirm-alert/delete-confirm-alert.component';

@Component({
  selector: 'participant-details',
  templateUrl: './participant-details.component.html',
  styleUrls: ['./participant-details.component.css']
})
export class ParticipantDetailsComponent implements OnInit {

  editing: boolean;
  participant: Participant;

  constructor(
    private dialog: MatDialog,
    private participantsService: MockParticipantsService,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.editing = false;
    this.getParticipant();
  }

  delete(): void {
    const dialogRef = this.dialog.open(DeleteConfirmAlertDialog, {
      data: {
        confirmMessage: 'Are you sure you want to delete?'
      },
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.router.navigate(['participants']);
        this.participantsService.deleteOne(this.participant.id);
      }
    });
  }

  edit(): void {
    this.editing = true;
  }

  save(participant: Participant): void {
    // @TODO dispatch suitable action to store
  }

  private getParticipant() {
    const id = + this.route.snapshot.paramMap.get('id');
    this.participantsService
      .find(id)
      .subscribe(participant => this.participant = participant);
  }

  goBack(): void {
    if (this.editing) {
      this.editing = false;
      return;
    } else {
      this.location.back();
    }
  }
}

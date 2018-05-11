import { Component, OnDestroy, OnInit } from '@angular/core';
import { Participant } from '../models/participant.model';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { DeleteConfirmAlertDialog } from '../../shared/delete-confirm-alert/delete-confirm-alert.component';
import { Store } from '@ngrx/store';
import { Participants } from '../store/participants-reducer';
import { ParticipantsSharedActions } from '../store/participants-actions';
import { Subject } from 'rxjs/Subject';
import { AppSelectors } from '../../core/store/app-selectors';
import { Parish } from '../../parish/models/parish.model';
import { Parishes } from '../../parish/store/parish-reducer';
import { ParticipantAddEditDialog } from '../add-edit/participant-dialog/add-edit-dialog.component';

@Component({
  selector: 'participant-details',
  templateUrl: './participant-details.component.html',
  styleUrls: ['./participant-details.component.css']
})
export class ParticipantDetailsComponent implements OnInit, OnDestroy {

  participant: Participant;
  parishes: Parish[];

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private dialog: MatDialog,
    private router: Router,
    private participantsStore: Store<Participants.State>,
    private parishStore: Store<Parishes.State>,
  ) { }

  ngOnInit(): void {
    this.participantsStore.select(AppSelectors.getSelectedParticipant)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((participant: Participant) => this.participant = participant);
    this.parishStore.select(AppSelectors.getParishList)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((parishes: Parish[]) => this.parishes = parishes);
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
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
        this.participantsStore.dispatch(new ParticipantsSharedActions.DeleteParticipant(this.participant.id));
      }
    });
  }

  edit(): void {
    const dialogRef = this.dialog.open(ParticipantAddEditDialog, {
      data: {
        dialogTitle: 'Edit participant',
        firstName: this.participant.firstName,
        lastName: this.participant.lastName,
        pesel: this.participant.pesel,
        parishId: this.participant.parishId,
        parishes: this.parishes,
        personalData: this.participant.personalData,
        address: this.participant.address,
        healthReport: this.participant.healthReport,
        experience: this.participant.experience
      },
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const p = Participant.mapFromForm(result);
        p.id = this.participant.id;
        this.participant = p;
        this.participantsStore.dispatch(new ParticipantsSharedActions.UpdateParticipant(p));
      }
    })
  }

  goBack(): void {
    this.router.navigate(['participants']);
  }
}

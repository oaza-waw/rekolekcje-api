import { Component, OnDestroy, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Participant } from '../../shared/models/participant.model';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { DeleteConfirmAlertDialog } from '../../shared/delete-confirm-alert/delete-confirm-alert.component';
import { Store } from '@ngrx/store';
import { Participants } from '../../core/store/participants/participants-reducer';
import { ParticipantsSharedActions } from '../../shared/store-shared/participants/participants-actions';
import { Subject } from 'rxjs/Subject';
import { AppSelectors } from '../../core/store/app-selectors';

@Component({
  selector: 'participant-details',
  templateUrl: './participant-details.component.html',
  styleUrls: ['./participant-details.component.css']
})
export class ParticipantDetailsComponent implements OnInit, OnDestroy {

  editing: boolean;
  participant: Participant;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  // @TODO bula z ta location, do poprawy. Tak sie nie robi
  constructor(
    private dialog: MatDialog,
    private router: Router,
    private store: Store<Participants.State>,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.editing = false;
    this.store.select(AppSelectors.getSelectedParticipant)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((participant: Participant) => this.participant = participant);
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
        this.store.dispatch(new ParticipantsSharedActions.DeleteParticipant(this.participant.id));
      }
    });
  }

  edit(): void {
    this.editing = true;
  }

  save(participant: Participant): void {
    if (!participant) {
      this.editing = false;
    }
    this.participant = participant;
    this.store.dispatch(new ParticipantsSharedActions.UpdateParticipant(participant));
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

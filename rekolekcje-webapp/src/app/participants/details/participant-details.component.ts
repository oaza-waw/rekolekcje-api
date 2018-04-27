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

@Component({
  selector: 'participant-details',
  templateUrl: './participant-details.component.html',
  styleUrls: ['./participant-details.component.css']
})
export class ParticipantDetailsComponent implements OnInit, OnDestroy {

  editing: boolean;
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
    this.editing = false;
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

  getParishName(parishId: number): string {
    let parish = this.findParish(parishId);
    return parish ? parish.name : '';
  }

  getParishAddress(parishId: number): string {
    let parish = this.findParish(parishId);
    return parish ? parish.address : '';
  }

  private findParish(parishId: number): Parish {
    return this.parishes.find(p => p.id === parishId);
  }

  edit(): void {
    this.editing = true;
  }

  save(participant: Participant): void {
    if (!participant) {
      this.editing = false;
    }
    this.participant = participant;
    this.participantsStore.dispatch(new ParticipantsSharedActions.UpdateParticipant(participant));
  }

  goBack(): void {
    if (this.editing) {
      this.editing = false;
      return;
    } else {
      this.router.navigate(['participants']);
    }
  }
}

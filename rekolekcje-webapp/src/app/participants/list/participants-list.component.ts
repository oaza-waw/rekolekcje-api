import {
  AfterViewInit,
  Component, EventEmitter, Input, OnChanges, Output,
  ViewChild
} from '@angular/core';
import { Participant } from '../../shared/model/participant.model';
import { MatDialog, MatDialogRef, MatPaginator, MatTableDataSource } from '@angular/material';
import { DeleteConfirmAlertDialog } from '../delete-confirm-alert/delete-confirm-alert.component';
import { ParticipantAddEditDialog } from '../add-edit/dialog/add-edit-dialog.component';

@Component({
  selector: 'participants-list',
  templateUrl: './participants-list.component.html',
  styleUrls: ['./participants-list.component.css']
})
export class ParticipantsListComponent implements OnChanges, AfterViewInit {

  @Input()
  participants: Participant[];

  @Output()
  addParticipant: EventEmitter<Participant> = new EventEmitter<Participant>();
  @Output()
  deleteParticipant: EventEmitter<number> = new EventEmitter<number>();
  @Output()
  editParticipant: EventEmitter<Participant> = new EventEmitter<Participant>();

  dataSource: MatTableDataSource<Participant>;
  displayedColumns = ['firstName', 'lastName', 'pesel', 'address', 'parish', 'options'];
  title = 'All participants';

  @ViewChild(MatPaginator)
  paginator: MatPaginator;

  constructor(public dialog: MatDialog) {
  }

  ngOnChanges(): void {
    this.dataSource = new MatTableDataSource<Participant>(this.participants);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  openAddParticipantDialog(): void {
    const dialogRef = this.dialog.open(ParticipantAddEditDialog, {
      data: {
        dialogTitle: 'Add new participant'
      },
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.addParticipant.emit(result);
      }
    })
  }

  openEditParticipantDialog(participant: Participant): void {
    const dialogRef = this.dialog.open(ParticipantAddEditDialog, {
      data: {
        dialogTitle: 'Edit participant',
        firstName: participant.firstName,
        lastName: participant.lastName,
        pesel: participant.pesel,
        address: participant.address,
        parish: participant.parish
      },
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.editParticipant.emit(result);
      }
    })
  }

  openConfirmDeleteAlert(id: number) {
    const dialogRef = this.dialog.open(DeleteConfirmAlertDialog, {
      data: {
        confirmMessage: 'Are you sure you want to delete?',
      },
      disableClose: false,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteParticipant.emit(id);
      }
    });
  }
}

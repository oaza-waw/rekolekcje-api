import {
  AfterViewInit,
  Component, EventEmitter, Input, OnChanges, Output,
  ViewChild
} from '@angular/core';
import { Participant } from '../models/participant.model';
import { MatDialog, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { DeleteConfirmAlertDialog } from '../../shared/delete-confirm-alert/delete-confirm-alert.component';
import { ParticipantAddEditDialog } from '../add-edit/participant-dialog/add-edit-dialog.component';
import { Parish } from '../../parish/models/parish.model';

@Component({
  selector: 'participants-list',
  templateUrl: './participants-list.component.html',
  styleUrls: ['./participants-list.component.css']
})
export class ParticipantsListComponent implements OnChanges, AfterViewInit {

  @Input()
  participants: Participant[];
  @Input()
  parishes: Parish[];

  @Output()
  addParticipant: EventEmitter<Participant> = new EventEmitter<Participant>();
  @Output()
  deleteParticipant: EventEmitter<number> = new EventEmitter<number>();
  @Output()
  editParticipant: EventEmitter<Participant> = new EventEmitter<Participant>();
  @Output()
  onSelectParticipant: EventEmitter<Participant> = new EventEmitter<Participant>();

  dataSource: MatTableDataSource<Participant>;
  displayedColumns = ['firstName', 'lastName', 'pesel', 'address', 'parish', 'options'];
  title = 'All participants';

  @ViewChild(MatPaginator)
  paginator: MatPaginator;

  @ViewChild(MatSort)
  sort: MatSort;

  constructor(public dialog: MatDialog) {
  }

  // @TODO refactor this to set input
  ngOnChanges(): void {
    this.dataSource = new MatTableDataSource<Participant>(this.participants);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  goToDetails(participant: Participant): void {
    this.onSelectParticipant.emit(participant);
  }

  openAddParticipantDialog(): void {
    const dialogRef = this.dialog.open(ParticipantAddEditDialog, {
      data: {
        dialogTitle: 'Add new participant',
        parishes: this.parishes
      },
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.addParticipant.emit(Participant.mapFromForm(result));
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
        parishId: participant.parishId,
        parishes: this.parishes,
        christeningDate: participant.personalData.christeningDate,
        christeningPlace: participant.personalData.christeningPlace,
        fatherName: participant.personalData.fatherName,
        motherName: participant.personalData.motherName,
        closeRelativeName: participant.personalData.closeRelativeName,
        closeRelativeNumber: participant.personalData.closeRelativeNumber,
        street: participant.address.street,
        number: participant.address.number,
        flat: participant.address.flat,
        code: participant.address.code,
        city: participant.address.city,
      },
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const p = Participant.mapFromForm(result);
        p.id = participant.id;
        this.editParticipant.emit(p);
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

  getParishName(parishId: number): string {
    let parish = this.parishes.find(p => p.id === parishId);
    return parish ? parish.name : '';
  }
}

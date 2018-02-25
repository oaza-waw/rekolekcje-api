import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Parish } from '../models/parish.model';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { ParishAddEditDialogComponent } from '../add-edit/parish-dialog/parish-dialog.component';
import { DeleteConfirmAlertDialog } from '../../shared/delete-confirm-alert/delete-confirm-alert.component';

@Component({
  selector: 'parish-list',
  templateUrl: './parish-list.component.html',
  styleUrls: ['./parish-list.component.css']
})
export class ParishListComponent {

  @Output()
  addParish: EventEmitter<Parish> = new EventEmitter<Parish>();
  @Output()
  deleteParish: EventEmitter<number> = new EventEmitter<number>();
  @Output()
  editParish: EventEmitter<Parish> = new EventEmitter<Parish>();

  dataSource: MatTableDataSource<Parish> = new MatTableDataSource<Parish>();
  displayedColumns = ['name', 'address', 'options'];

  constructor(public dialog: MatDialog) {
  }

  @Input()
  set parishes(parishes: Parish[]) {
    this.dataSource.data = parishes;
  }

  openAddParishDialog(): void {
    const dialogRef = this.dialog.open(ParishAddEditDialogComponent, {
      data: {
        dialogTitle: 'Add new parish'
      },
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.addParish.emit(Parish.mapFromForm(result));
      }
    })
  }

  openEditParishDialog(parish: Parish): void {
    const dialogRef = this.dialog.open(ParishAddEditDialogComponent, {
      data: {
        dialogTitle: 'Edit parish',
        name: parish.name,
        address: parish.address,
      },
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('after closed. Result: ' + result);
      if (result) {
        const p = Parish.mapFromForm(result);
        p.id = parish.id;
        console.log('after edit: ' + p.name);
        this.editParish.emit(p);
      }
    })
  }

  openConfirmDeleteAlert(id: number): void {
    const dialogRef = this.dialog.open(DeleteConfirmAlertDialog, {
      data: {
        confirmMessage: 'Are you sure you want to delete?',
      },
      disableClose: false,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteParish.emit(id);
      }
    });
  }
}

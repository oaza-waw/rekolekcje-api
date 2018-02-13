import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Parish } from '../../shared/models/parish.model';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { ParishAddEditDialogComponent } from '../add-edit/parish-dialog/parish-dialog.component';

@Component({
  selector: 'parish-list',
  templateUrl: './parish-list.component.html',
  styleUrls: ['./parish-list.component.css']
})
export class ParishListComponent {

  @Output()
  addParish: EventEmitter<Parish> = new EventEmitter<Parish>();

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

}

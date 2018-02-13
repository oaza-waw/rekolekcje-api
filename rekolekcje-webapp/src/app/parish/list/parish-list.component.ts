import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { Parish } from '../../shared/models/parish.model';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { ParishAddEditDialogComponent } from '../add-edit/parish-dialog/parish-dialog.component';

@Component({
  selector: 'parish-list',
  templateUrl: './parish-list.component.html',
  styleUrls: ['./parish-list.component.css']
})
export class ParishListComponent implements OnChanges {

  @Input()
  parishes: Parish[];

  @Output()
  addParish: EventEmitter<Parish> = new EventEmitter<Parish>();

  dataSource: MatTableDataSource<Parish>;
  displayedColumns = ['name', 'address', 'options'];

  constructor(public dialog: MatDialog) {
  }

  ngOnChanges(): void {
    this.dataSource = new MatTableDataSource<Parish>(this.parishes);
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

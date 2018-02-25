import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Parish } from '../../models/parish.model';

@Component({
  selector: 'parish-dialog',
  templateUrl: './parish-dialog.component.html',
  styleUrls: ['./parish-dialog.component.css']
})
export class ParishAddEditDialogComponent implements OnInit {

  id: number;
  name: string;
  address: string;

  dialogTitle: string;

  constructor(public dialogRef: MatDialogRef<ParishAddEditDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any){
    this.dialogTitle = data.dialogTitle;
    data.name ? this.name = data.name : this.name = '';
    data.address ? this.address = data.address : this.address = '';
  }

  ngOnInit() {
  }

  onFormOutput(event: Parish): void {
    if (event) {
      event.id = this.id;
      this.dialogRef.close({ result: event });
    } else {
      this.dialogRef.close();
    }
  }

}

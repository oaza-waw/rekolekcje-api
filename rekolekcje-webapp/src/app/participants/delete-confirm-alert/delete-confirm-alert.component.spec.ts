import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteConfirmAlertDialog } from './delete-confirm-alert.component';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ParticipantsModule } from '../participants.module';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [DeleteConfirmAlertDialog],
  entryComponents: [DeleteConfirmAlertDialog],
  exports: [DeleteConfirmAlertDialog],
  imports: [
    CommonModule,
    MatDialogModule,
    BrowserAnimationsModule,
  ]
})
class ParticipantsDeleteConfirmAlertComponentSpecModule { }

describe('DeleteConfirmAlertDialog', () => {
  let component: DeleteConfirmAlertDialog;
  // let fixture: ComponentFixture<DeleteConfirmAlertDialog>;
  let dialog: MatDialog;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ParticipantsDeleteConfirmAlertComponentSpecModule
      ],
    });
  }));

  beforeEach(() => {
    // fixture = TestBed.createComponent(DeleteConfirmAlertDialog);
    dialog = TestBed.get(MatDialog);
    let dialogRef = dialog.open(DeleteConfirmAlertDialog);
    component = dialogRef.componentInstance;
    // fixture.detectChanges();
  });

  //TODO: enable test for this dialog
  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});

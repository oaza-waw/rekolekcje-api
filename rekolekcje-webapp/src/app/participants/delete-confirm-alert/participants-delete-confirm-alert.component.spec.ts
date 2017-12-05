import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantsDeleteConfirmAlertComponent } from './participants-delete-confirm-alert.component';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ParticipantsModule } from '../participants.module';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [ParticipantsDeleteConfirmAlertComponent],
  entryComponents: [ParticipantsDeleteConfirmAlertComponent],
  exports: [ParticipantsDeleteConfirmAlertComponent],
  imports: [
    CommonModule,
    MatDialogModule,
    BrowserAnimationsModule,
  ]
})
class ParticipantsDeleteConfirmAlertComponentSpecModule { }

describe('ParticipantsDeleteConfirmAlertComponent', () => {
  let component: ParticipantsDeleteConfirmAlertComponent;
  // let fixture: ComponentFixture<ParticipantsDeleteConfirmAlertComponent>;
  let dialog: MatDialog;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ParticipantsDeleteConfirmAlertComponentSpecModule
      ],
    });
  }));

  beforeEach(() => {
    // fixture = TestBed.createComponent(ParticipantsDeleteConfirmAlertComponent);
    dialog = TestBed.get(MatDialog);
    let dialogRef = dialog.open(ParticipantsDeleteConfirmAlertComponent);
    component = dialogRef.componentInstance;
    // fixture.detectChanges();
  });

  //TODO: enable test for this dialog
  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});

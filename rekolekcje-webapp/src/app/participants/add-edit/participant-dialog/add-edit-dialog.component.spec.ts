import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantAddEditDialog } from './add-edit-dialog.component';
import { ParticipantsModule } from '../../participants.module';
import { MaterialModule } from '../../../shared/material/material.module';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { AppReducer } from '../../../core/store/app-store';
import { StoreModule } from '@ngrx/store';
import { ParishReducer } from '../../../core/store/parish/parish-reducer';

class MatDialogRefMock {
}

describe('ParticipantAddEditDialog', () => {
  let component: ParticipantAddEditDialog;
  let fixture: ComponentFixture<ParticipantAddEditDialog>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        StoreModule.forRoot(AppReducer.reducer),
        StoreModule.forFeature('parishModule', ParishReducer.reducer),
        ParticipantsModule,
        MaterialModule
      ],
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MatDialogRef, useClass: MatDialogRefMock }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantAddEditDialog);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

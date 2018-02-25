import { StoreModule } from '@ngrx/store';
import { AppReducer } from '../core/store/app-store';
import { ParticipantsReducer } from '../core/store/participants/participants-reducer';
import { ParishReducer } from '../core/store/parish/parish-reducer';
import { RouterTestingModule } from '@angular/router/testing';
import { ParticipantsModule } from './participants.module';
import { SharedModule } from '../shared/shared.module';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

export class MatDialogRefMock {
}

export let participantsTestingModule = {
  imports: [
    StoreModule.forRoot(AppReducer.reducer),
    StoreModule.forFeature('participantsModule', ParticipantsReducer.reducer),
    StoreModule.forFeature('parishModule', ParishReducer.reducer),
    RouterTestingModule,
    ParticipantsModule,
    SharedModule,
    NoopAnimationsModule
  ],
  providers: [
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useClass: MatDialogRefMock }
  ]
};

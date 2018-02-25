import { StoreModule } from '@ngrx/store';
import { AppReducer } from '../core/store/app-store';
import { ParishReducer } from './store/parish-reducer';
import { ParishModule } from './parish.module';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

export class MatDialogRefMock {
}

export let parishTestingModule = {
  imports: [
    StoreModule.forRoot(AppReducer.reducer),
    StoreModule.forFeature('parishModule', ParishReducer.reducer),
    ParishModule,
    NoopAnimationsModule
  ],
  providers: [
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useClass: MatDialogRefMock }
  ]
};

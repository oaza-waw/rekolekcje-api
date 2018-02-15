import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishAddEditDialogComponent } from './parish-dialog.component';
import { MaterialModule } from '../../../shared/material/material.module';
import { ParishModule } from '../../parish.module';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

class MatDialogRefMock {
}

describe('ParishAddEditDialogComponent', () => {
  let component: ParishAddEditDialogComponent;
  let fixture: ComponentFixture<ParishAddEditDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ParishModule,
        MaterialModule,
        BrowserAnimationsModule,
      ],
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MatDialogRef, useClass: MatDialogRefMock }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParishAddEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

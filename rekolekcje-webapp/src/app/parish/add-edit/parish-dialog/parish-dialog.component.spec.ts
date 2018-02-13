import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishAddEditDialogComponent } from './parish-dialog.component';

describe('ParishAddEditDialogComponent', () => {
  let component: ParishAddEditDialogComponent;
  let fixture: ComponentFixture<ParishAddEditDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParishAddEditDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParishAddEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});

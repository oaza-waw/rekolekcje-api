import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantAddEditDialog } from './add-edit-dialog.component';

describe('ParticipantAddEditDialog', () => {
  let component: ParticipantAddEditDialog;
  let fixture: ComponentFixture<ParticipantAddEditDialog>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParticipantAddEditDialog ]
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

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantAddEditComponent } from './participant-add-edit.component';

describe('ParticipantAddEditComponent', () => {
  let component: ParticipantAddEditComponent;
  let fixture: ComponentFixture<ParticipantAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParticipantAddEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

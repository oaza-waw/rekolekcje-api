import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantAddEditComponent } from './participant-add-edit.component';
import { participantsTestingModule } from '../../participants-testing.module';

describe('ParticipantAddEditComponent', () => {
  let component: ParticipantAddEditComponent;
  let fixture: ComponentFixture<ParticipantAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(participantsTestingModule).compileComponents();
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

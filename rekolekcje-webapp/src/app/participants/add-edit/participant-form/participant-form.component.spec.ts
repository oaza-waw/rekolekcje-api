import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantFormComponent } from './participant-form.component';
import { participantsTestingModule } from '../../participants-testing.module';

describe('ParticipantFormComponent', () => {
  let component: ParticipantFormComponent;
  let fixture: ComponentFixture<ParticipantFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(participantsTestingModule).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantFormComponent);
    component = fixture.componentInstance;
    component.address = {};
    component.personalData = {};
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantAddEditComponent } from './participant-add-edit.component';
import { ParticipantsModule } from '../../participants.module';
import { RouterTestingModule } from '@angular/router/testing';

describe('ParticipantAddEditComponent', () => {
  let component: ParticipantAddEditComponent;
  let fixture: ComponentFixture<ParticipantAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ParticipantsModule, RouterTestingModule]
      // declarations: [ ParticipantAddEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //TODO: fix after switching to ChromeHeadless
  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});

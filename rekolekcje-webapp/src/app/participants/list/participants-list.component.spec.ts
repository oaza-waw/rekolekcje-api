import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantsListComponent } from './participants-list.component';
import { participantsTestingModule } from '../participants-testing.module';

describe('ParticipantsListComponent', () => {
  let component: ParticipantsListComponent;
  let fixture: ComponentFixture<ParticipantsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(participantsTestingModule).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //TODO: fix after resolving problems with pagination
  // it('should be created', () => {
  //   expect(component).toBeTruthy();
  // });

  /*
   * TODO: Implement tests for changing state. This might also be done in participants.component.spec.ts.
   * See https://github.com/ngrx/platform/blob/master/docs/store/testing.md for details on testing
   */
});

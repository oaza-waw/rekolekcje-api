import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantDetailsComponent } from './participant-details.component';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Store } from '@ngrx/store';
import { Participants } from '../../core/store/participants/participants-reducer';
import { participantsTestingModule } from '../participants-testing.module';

describe('ParticipantDetailsComponent', () => {
  let component: ParticipantDetailsComponent;
  let fixture: ComponentFixture<ParticipantDetailsComponent>;
  let router: Router;
  let location: Location;
  let store: Store<Participants.State>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(participantsTestingModule);
    store = TestBed.get(Store);
    spyOn(store, 'dispatch').and.callThrough();
    router = TestBed.get(Router);
    location = TestBed.get(Location);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

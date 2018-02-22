import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantDetailsComponent } from './participant-details.component';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { RouterTestingModule } from '@angular/router/testing';
import { ParticipantsModule } from '../participants.module';
import { Store, StoreModule } from '@ngrx/store';
import {
  Participants,
  ParticipantsReducer
} from '../../core/store/participants/participants-reducer';
import { AppReducer } from '../../core/store/app-store';
import { ParishReducer } from '../../core/store/parish/parish-reducer';

describe('ParticipantDetailsComponent', () => {
  let component: ParticipantDetailsComponent;
  let fixture: ComponentFixture<ParticipantDetailsComponent>;
  let router: Router;
  let location: Location;
  let store: Store<Participants.State>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        StoreModule.forRoot(AppReducer.reducer),
        StoreModule.forFeature('participantsModule', ParticipantsReducer.reducer),
        StoreModule.forFeature('parishModule', ParishReducer.reducer),
        RouterTestingModule,
        ParticipantsModule
      ],
    });
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

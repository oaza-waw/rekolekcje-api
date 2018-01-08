import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantDetailsComponent } from './participant-details.component';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { RouterTestingModule } from '@angular/router/testing';
import { MaterialModule } from '../../shared/material/material.module';
import { ParticipantsModule } from '../participants.module';

describe('ParticipantDetailsComponent', () => {
  let component: ParticipantDetailsComponent;
  let fixture: ComponentFixture<ParticipantDetailsComponent>;
  let router: Router;
  let location: Location;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule, ParticipantsModule, MaterialModule ],
      // declarations: [ ParticipantDetailsComponent ],
    })
    .compileComponents();
    router = TestBed.get(Router);
    location = TestBed.get(Location);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //TODO: fix test to provide ngrx/Store. See https://github.com/ngrx/platform/blob/master/docs/store/testing.md
  // it('should be created', () => {
  //   expect(component).toBeTruthy();
  // });
});

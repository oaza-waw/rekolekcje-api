import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantDetailsComponent } from './participant-details.component';
import {MockParticipantsService} from "../mock-participants.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {RouterTestingModule} from "@angular/router/testing";

describe('ParticipantDetailsComponent', () => {
  let component: ParticipantDetailsComponent;
  let fixture: ComponentFixture<ParticipantDetailsComponent>;
  let router: Router;
  let location: Location;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule ],
      declarations: [ ParticipantDetailsComponent ],
      providers: [ MockParticipantsService ]
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

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

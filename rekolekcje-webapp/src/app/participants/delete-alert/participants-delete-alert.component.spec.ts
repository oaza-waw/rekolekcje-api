import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantsDeleteAlertComponent } from './participants-delete-alert.component';

describe('ParticipantsDeleteAlertComponent', () => {
  let component: ParticipantsDeleteAlertComponent;
  let fixture: ComponentFixture<ParticipantsDeleteAlertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParticipantsDeleteAlertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantsDeleteAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

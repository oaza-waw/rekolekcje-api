import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantsAddNewComponent } from './participants-add-new.component';

describe('ParticipantsAddNewComponent', () => {
  let component: ParticipantsAddNewComponent;
  let fixture: ComponentFixture<ParticipantsAddNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParticipantsAddNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantsAddNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

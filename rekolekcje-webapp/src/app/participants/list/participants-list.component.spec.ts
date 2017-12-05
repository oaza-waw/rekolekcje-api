import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantsListComponent } from './participants-list.component';
import { RouterModule } from '@angular/router';
import { MatDialogModule } from '@angular/material';

describe('ParticipantsListComponent', () => {
  let component: ParticipantsListComponent;
  let fixture: ComponentFixture<ParticipantsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterModule, MatDialogModule],
      declarations: [ParticipantsListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

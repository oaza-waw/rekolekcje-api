import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantAddEditComponent } from './participant-add-edit.component';
import { ParticipantsModule } from '../../participants.module';
import { RouterTestingModule } from '@angular/router/testing';
import { StoreModule } from '@ngrx/store';
import { AppReducer } from '../../../core/store/app-store';
import { ParticipantsReducer } from '../../../core/store/participants/participants-reducer';

describe('ParticipantAddEditComponent', () => {
  let component: ParticipantAddEditComponent;
  let fixture: ComponentFixture<ParticipantAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        StoreModule.forRoot(AppReducer.reducer),
        StoreModule.forFeature('participantsModule', ParticipantsReducer.reducer),
        ParticipantsModule,
        RouterTestingModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { PersonalDataComponent } from './personal-data.component';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { participantsTestingModule } from '../../participants-testing.module';

describe('PersonalDataComponent', () => {
  let component: PersonalDataComponent;
  let fixture: ComponentFixture<PersonalDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(participantsTestingModule).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalDataComponent);
    component = fixture.componentInstance;
    component.personalData = {};
    component.address = {};
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

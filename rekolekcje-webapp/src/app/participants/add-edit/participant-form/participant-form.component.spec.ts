import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantFormComponent } from './participant-form.component';
import { participantsTestingModule } from '../../participants-testing.module';
import {Component, ViewChild} from "@angular/core";

describe('ParticipantFormComponent', () => {
  let component: ParticipantFormComponent;
  let fixture: ComponentFixture<ParticipantFormComponent>;
  let hostFixture: ComponentFixture<TestHostComponent>;
  let hostComponent: TestHostComponent;

  beforeEach(async(() => {
    TestBed.configureTestingModule(participantsTestingModule).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantFormComponent);
    hostFixture = TestBed.createComponent(TestHostComponent);
    component = fixture.componentInstance;
    hostComponent = hostFixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   hostComponent.componentUnderTestComponent.address = {
  //     street: '',
  //     number: null,
  //     flat: null,
  //     city: '',
  //     code: '',
  //   };
  //   expect(component).toBeTruthy();
  // });
});

@Component({
  selector: `host-component`,
  template: `<participiant-form></participiant-form>`
})
class TestHostComponent {
  @ViewChild(ParticipantFormComponent)
  public componentUnderTestComponent: ParticipantFormComponent;
}

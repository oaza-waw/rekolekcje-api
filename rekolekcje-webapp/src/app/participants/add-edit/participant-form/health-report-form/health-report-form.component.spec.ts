import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthReportFormComponent } from './health-report-form.component';
import { FormControl, FormGroup } from "@angular/forms";

describe('HealthReportFormComponent', () => {
  let component: HealthReportFormComponent;
  let fixture: ComponentFixture<HealthReportFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HealthReportFormComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HealthReportFormComponent);
    component = fixture.componentInstance;
    component.healthReportData = {};
    component.healthReportForm = new FormGroup({
      currentTreatment: new FormControl(),
      mentalDisorders: new FormControl(),
      medications: new FormControl(),
      allergies: new FormControl(),
      medicalDiet: new FormControl(),
      canHike: new FormControl(),
      illnessHistory: new FormControl(),
      hasMotionSickness: new FormControl(),
      other: new FormControl()
    });
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthReportComponent } from './health-report.component';

describe('HealthReportComponent', () => {
  let component: HealthReportComponent;
  let fixture: ComponentFixture<HealthReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HealthReportComponent ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HealthReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

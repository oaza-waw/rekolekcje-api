import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoricalRetreatFormComponent } from './historical-retreat-form.component';

describe('HistoricalRetreatFormComponent', () => {
  let component: HistoricalRetreatFormComponent;
  let fixture: ComponentFixture<HistoricalRetreatFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoricalRetreatFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoricalRetreatFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

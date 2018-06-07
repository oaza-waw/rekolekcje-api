import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoricalRetreatsDetailsComponent } from './historical-retreats-details.component';

describe('HistoricalRetreatsDetailsComponent', () => {
  let component: HistoricalRetreatsDetailsComponent;
  let fixture: ComponentFixture<HistoricalRetreatsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoricalRetreatsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoricalRetreatsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

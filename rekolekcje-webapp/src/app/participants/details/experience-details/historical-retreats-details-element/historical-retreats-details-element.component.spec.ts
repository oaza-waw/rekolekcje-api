import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoricalRetreatsDetailsElementComponent } from './historical-retreats-details-element.component';

describe('HistoricalRetreatsDetailsElementComponent', () => {
  let component: HistoricalRetreatsDetailsElementComponent;
  let fixture: ComponentFixture<HistoricalRetreatsDetailsElementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoricalRetreatsDetailsElementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoricalRetreatsDetailsElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

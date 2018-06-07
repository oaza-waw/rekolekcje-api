import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoricalRetreatsArrayComponent } from './historical-retreats-array.component';

describe('HistoricalRetreatsArrayComponent', () => {
  let component: HistoricalRetreatsArrayComponent;
  let fixture: ComponentFixture<HistoricalRetreatsArrayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoricalRetreatsArrayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoricalRetreatsArrayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

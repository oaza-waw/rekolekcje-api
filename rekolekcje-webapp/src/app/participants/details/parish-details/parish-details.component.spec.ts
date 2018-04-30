import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishDetailsComponent } from './parish-details.component';

describe('ParishDetailsComponent', () => {
  let component: ParishDetailsComponent;
  let fixture: ComponentFixture<ParishDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParishDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParishDetailsComponent);
    component = fixture.componentInstance;
    component.parishes = [];
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

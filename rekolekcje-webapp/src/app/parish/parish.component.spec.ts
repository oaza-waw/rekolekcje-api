import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishComponent } from './parish.component';

describe('ParishComponent', () => {
  let component: ParishComponent;
  let fixture: ComponentFixture<ParishComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParishComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

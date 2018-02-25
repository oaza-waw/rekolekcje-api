import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishComponent } from './parish.component';
import { parishTestingModule } from './parish-testing.module';

describe('ParishComponent', () => {
  let component: ParishComponent;
  let fixture: ComponentFixture<ParishComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(parishTestingModule).compileComponents();
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

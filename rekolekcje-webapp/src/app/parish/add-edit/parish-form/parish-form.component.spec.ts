import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishFormComponent } from './parish-form.component';
import { parishTestingModule } from '../../parish-testing.module';

describe('ParishFormComponent', () => {
  let component: ParishFormComponent;
  let fixture: ComponentFixture<ParishFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(parishTestingModule).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParishFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

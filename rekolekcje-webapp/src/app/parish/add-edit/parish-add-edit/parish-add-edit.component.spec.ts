import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishAddEditComponent } from './parish-add-edit.component';
import { parishTestingModule } from '../../parish-testing.module';

describe('ParishAddEditComponent', () => {
  let component: ParishAddEditComponent;
  let fixture: ComponentFixture<ParishAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(parishTestingModule).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParishAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

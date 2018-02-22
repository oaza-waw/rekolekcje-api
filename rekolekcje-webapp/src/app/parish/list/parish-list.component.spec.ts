import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishListComponent } from './parish-list.component';
import { parishTestingModule } from '../parish-testing.module';

describe('ParishListComponent', () => {
  let component: ParishListComponent;
  let fixture: ComponentFixture<ParishListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule(parishTestingModule).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParishListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

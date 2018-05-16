import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsTextFieldComponent } from './details-text-field.component';

describe('DetailsTextFieldComponent', () => {
  let component: DetailsTextFieldComponent;
  let fixture: ComponentFixture<DetailsTextFieldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailsTextFieldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsTextFieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

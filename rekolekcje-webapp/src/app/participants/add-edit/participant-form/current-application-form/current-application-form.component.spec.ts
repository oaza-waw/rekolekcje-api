import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentApplicationFormComponent } from './current-application-form.component';

describe('CurrentApplicationFormComponent', () => {
  let component: CurrentApplicationFormComponent;
  let fixture: ComponentFixture<CurrentApplicationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentApplicationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentApplicationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentApplicationDetailsComponent } from './current-application-details.component';

describe('CurrentApplicationDetailsComponent', () => {
  let component: CurrentApplicationDetailsComponent;
  let fixture: ComponentFixture<CurrentApplicationDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentApplicationDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentApplicationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

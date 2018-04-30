import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpinionsDetailsComponent } from './opinions-details.component';

describe('OpinionsDetailsComponent', () => {
  let component: OpinionsDetailsComponent;
  let fixture: ComponentFixture<OpinionsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpinionsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpinionsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

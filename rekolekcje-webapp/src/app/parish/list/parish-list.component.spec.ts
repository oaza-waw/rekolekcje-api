import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishListComponent } from './parish-list.component';

describe('ParishListComponent', () => {
  let component: ParishListComponent;
  let fixture: ComponentFixture<ParishListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParishListComponent ]
    })
    .compileComponents();
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

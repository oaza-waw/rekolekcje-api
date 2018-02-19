import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishComponent } from './parish.component';
import { ParishModule } from './parish.module';
import { StoreModule } from '@ngrx/store';
import { AppReducer } from '../core/store/app-store';
import { ParishReducer } from '../core/store/parish/parish-reducer';

describe('ParishComponent', () => {
  let component: ParishComponent;
  let fixture: ComponentFixture<ParishComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        StoreModule.forRoot(AppReducer.reducer),
        StoreModule.forFeature('parishModule', ParishReducer.reducer),
        ParishModule
      ]
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

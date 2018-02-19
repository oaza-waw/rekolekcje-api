import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishAddEditComponent } from './parish-add-edit.component';
import { ParishModule } from '../../parish.module';
import { StoreModule } from '@ngrx/store';
import { AppReducer } from '../../../core/store/app-store';
import { ParishReducer } from '../../../core/store/parish/parish-reducer';

describe('ParishAddEditComponent', () => {
  let component: ParishAddEditComponent;
  let fixture: ComponentFixture<ParishAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        StoreModule.forRoot(AppReducer.reducer),
        StoreModule.forFeature('parishModule', ParishReducer.reducer),
        ParishModule,
      ]
    })
    .compileComponents();
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

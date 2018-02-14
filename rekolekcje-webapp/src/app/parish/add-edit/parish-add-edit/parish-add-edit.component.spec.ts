import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishAddEditComponent } from './parish-add-edit.component';
import { ParishModule } from '../../parish.module';

describe('ParishAddEditComponent', () => {
  let component: ParishAddEditComponent;
  let fixture: ComponentFixture<ParishAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ ParishModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParishAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});

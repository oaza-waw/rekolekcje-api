import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParishFormComponent } from './parish-form.component';
import { ParishModule } from '../../parish.module';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

describe('ParishFormComponent', () => {
  let component: ParishFormComponent;
  let fixture: ComponentFixture<ParishFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ParishModule,
        NoopAnimationsModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParishFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

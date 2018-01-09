import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainMenuBarComponent } from './main-menu-bar.component';
import { RouterTestingModule } from '@angular/router/testing';

describe('MainMenuBarComponent', () => {
  let component: MainMenuBarComponent;
  let fixture: ComponentFixture<MainMenuBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [ MainMenuBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainMenuBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

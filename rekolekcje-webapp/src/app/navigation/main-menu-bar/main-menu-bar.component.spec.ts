import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainMenuBarComponent } from './main-menu-bar.component';
import { NavigationModule } from '../navigation.module';
import { RouterTestingModule } from '@angular/router/testing';
import { MaterialModule } from '../../shared/material/material.module';
import { UserMenuComponent } from '../user-menu/user-menu.component';
import { AuthModule } from '../../auth/auth.module';

describe('MainMenuBarComponent', () => {
  let component: MainMenuBarComponent;
  let fixture: ComponentFixture<MainMenuBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        // NavigationModule,
        MaterialModule,
        RouterTestingModule,
        AuthModule
      ],
      declarations: [
        MainMenuBarComponent,
        UserMenuComponent
      ]
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

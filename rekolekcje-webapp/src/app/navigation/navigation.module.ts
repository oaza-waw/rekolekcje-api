import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainMenuBarComponent } from './main-menu-bar/main-menu-bar.component';
import { RouterModule } from '@angular/router';
import { AuthModule } from '../auth/auth.module';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { MaterialModule } from '../shared/material/material.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    CommonModule,
    RouterModule,
    MaterialModule,
    AuthModule
  ],
  declarations: [
    MainMenuBarComponent,
    UserMenuComponent
  ],
  exports: [
    MainMenuBarComponent,
    // RouterModule
  ]
})
export class NavigationModule { }

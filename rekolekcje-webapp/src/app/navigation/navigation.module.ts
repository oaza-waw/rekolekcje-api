import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainMenuBarComponent } from './main-menu-bar/main-menu-bar.component';
import { RouterModule } from '@angular/router';
import { AuthModule } from '../auth/auth.module';
import { RouterModule } from '@angular/router';
import { MatTabsModule, MatToolbarModule } from '@angular/material';
import { UserMenuComponent } from './user-menu/user-menu.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    CommonModule,
    RouterModule,
    MatToolbarModule,
    MatTabsModule,
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

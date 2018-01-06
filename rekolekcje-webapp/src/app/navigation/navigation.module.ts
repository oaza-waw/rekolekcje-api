import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainMenuBarComponent } from './main-menu-bar/main-menu-bar.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [
    MainMenuBarComponent
  ],
  exports: [
    MainMenuBarComponent,
    // RouterModule
  ]
})
export class NavigationModule { }

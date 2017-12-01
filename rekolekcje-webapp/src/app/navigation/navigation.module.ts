import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainMenuBarComponent } from './main-menu-bar/main-menu-bar.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    MainMenuBarComponent
  ],
  exports: [
    MainMenuBarComponent
  ]
})
export class NavigationModule { }

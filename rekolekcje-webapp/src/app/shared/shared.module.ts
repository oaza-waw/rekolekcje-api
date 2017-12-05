import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalComponent } from './modal/modal.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    ModalComponent,
    PageNotFoundComponent
  ],
  exports: [
    ModalComponent
  ]
})
export class SharedModule { }

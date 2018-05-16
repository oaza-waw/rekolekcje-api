import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MaterialModule } from './material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DetailsModule } from './details/details.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MaterialModule,
    ReactiveFormsModule,
    DetailsModule,
  ],
  declarations: [
    PageNotFoundComponent
  ],
  exports: [
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    DetailsModule
  ],
})
export class SharedModule { }

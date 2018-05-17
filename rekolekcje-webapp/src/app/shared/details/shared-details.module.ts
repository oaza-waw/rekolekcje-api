import { NgModule } from '@angular/core';
import { DetailsTextFieldComponent } from './details-text-field/details-text-field.component';
import { MaterialModule } from '../material/material.module';

@NgModule({
  imports: [
    MaterialModule
  ],
  declarations: [
    DetailsTextFieldComponent
  ],
  exports: [
    DetailsTextFieldComponent
  ],
})
export class SharedDetailsModule { }

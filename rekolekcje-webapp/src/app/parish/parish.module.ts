import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParishComponent } from './parish.component';
import { ParishRoutingModule } from './parish-routing.module';
import { ParishListComponent } from './list/parish-list.component';
import { MaterialModule } from '../shared/material/material.module';
import { SharedModule } from '../shared/shared.module';
import { ParishFormComponent } from './add-edit/parish-form/parish-form.component';
import { ParishAddEditComponent } from './add-edit/parish-add-edit/parish-add-edit.component';
import { ParishAddEditDialogComponent } from './add-edit/parish-dialog/parish-dialog.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    MaterialModule,
    ParishRoutingModule
  ],
  declarations: [
    ParishComponent,
    ParishListComponent,
    ParishFormComponent,
    ParishAddEditComponent,
    ParishAddEditDialogComponent,
  ],
  exports: [
    ParishComponent
  ],
  entryComponents: [
    ParishAddEditDialogComponent,
  ]
})
export class ParishModule { }

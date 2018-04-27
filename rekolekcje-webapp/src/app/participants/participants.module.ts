import { NgModule } from '@angular/core';
import { ParticipantsComponent } from './participants.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ParticipantDetailsComponent } from './details/participant-details.component';
import { SharedModule } from '../shared/shared.module';
import { ParticipantsListComponent } from './list/participants-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DeleteConfirmAlertDialog } from '../shared/delete-confirm-alert/delete-confirm-alert.component';
import { ParticipantAddEditDialog } from './add-edit/participant-dialog/add-edit-dialog.component';
import { ParticipantFormComponent } from './add-edit/participant-form/participant-form.component';
import { ParticipantAddEditComponent } from './add-edit/participant-add-edit/participant-add-edit.component';
import { MaterialModule } from '../shared/material/material.module';
import { ParticipantsRoutingModule } from './participants-routing.module';
import {PersonalDataComponent} from "./details/personal-data/personal-data.component";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    BrowserAnimationsModule,
    MaterialModule,
    ParticipantsRoutingModule
  ],
  declarations: [
    ParticipantAddEditDialog,
    DeleteConfirmAlertDialog,
    ParticipantFormComponent,
    ParticipantsComponent,
    ParticipantDetailsComponent,
    PersonalDataComponent,
    ParticipantsListComponent,
    ParticipantAddEditComponent,
  ],
  providers: [],
  exports: [
    ParticipantsComponent
  ],
  entryComponents: [
    ParticipantAddEditDialog,
    DeleteConfirmAlertDialog
  ]
})
export class ParticipantsModule {}

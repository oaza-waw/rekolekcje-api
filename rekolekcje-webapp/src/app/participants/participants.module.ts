import { NgModule } from '@angular/core';
import { ParticipantsComponent } from './participants.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ParticipantDetailsComponent } from './details/participant-details.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { ParticipantsListComponent } from './list/participants-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatButtonModule, MatCardModule, MatDialogModule, MatPaginatorModule,
  MatTableModule
} from '@angular/material';
import { DeleteConfirmAlertDialog } from '../shared/delete-confirm-alert/delete-confirm-alert.component';
import { ParticipantAddEditDialog } from './add-edit/participant-dialog/add-edit-dialog.component';
import { ParticipantFormComponent } from './add-edit/participant-form/participant-form.component';
import { ParticipantAddEditComponent } from './add-edit/participant-add-edit/participant-add-edit.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatCardModule,
    MatButtonModule,
    RouterModule
  ],
  declarations: [
    ParticipantAddEditDialog,
    DeleteConfirmAlertDialog,
    ParticipantFormComponent,
    ParticipantsComponent,
    ParticipantDetailsComponent,
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

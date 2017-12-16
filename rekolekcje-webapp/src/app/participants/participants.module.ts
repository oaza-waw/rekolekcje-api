import { NgModule } from '@angular/core';
import { ParticipantsComponent } from './participants.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MockParticipantsService } from './mock-participants.service';
import { ParticipantDetailsComponent } from './details/participant-details.component';
import { RouterModule } from '@angular/router';
import { ParticipantsAddNewComponent } from './old-add-new/participants-add-new.component';
import { SharedModule } from '../shared/shared.module';
import { ParticipantsListComponent } from './list/participants-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatButtonModule, MatCardModule, MatDialogModule, MatPaginatorModule,
  MatTableModule
} from '@angular/material';
import { DeleteConfirmAlertDialog } from './delete-confirm-alert/delete-confirm-alert.component';
import { ParticipantAddEditDialog } from './add-edit/dialog/add-edit-dialog.component';
import { ParticipantFormComponent } from './add-edit/form/participant-form.component';
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
    ParticipantsAddNewComponent,
    ParticipantsListComponent,
    ParticipantAddEditComponent,
  ],
  providers: [
    MockParticipantsService
  ],
  exports: [
    ParticipantsComponent
  ],
  entryComponents: [
    ParticipantAddEditDialog,
    DeleteConfirmAlertDialog
  ]
})
export class ParticipantsModule {}

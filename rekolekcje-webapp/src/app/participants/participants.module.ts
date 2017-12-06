import { NgModule } from '@angular/core';
import { ParticipantsComponent } from './participants.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MockParticipantsService } from './mock-participants.service';
import { ParticipantDetailsComponent } from './details/participant-details.component';
import { RouterModule } from '@angular/router';
import { ParticipantsAddNewComponent } from './add-new/participants-add-new.component';
import { SharedModule } from '../shared/shared.module';
import { ParticipantsListComponent } from './list/participants-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatCardModule, MatDialogModule, MatTableModule } from '@angular/material';
import { ParticipantsDeleteConfirmAlertComponent } from './delete-confirm-alert/participants-delete-confirm-alert.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    RouterModule
  ],
  declarations: [
    ParticipantsComponent,
    ParticipantDetailsComponent,
    ParticipantsAddNewComponent,
    ParticipantsDeleteConfirmAlertComponent,
    ParticipantsListComponent
  ],
  providers: [
    MockParticipantsService
  ],
  exports: [
    ParticipantsComponent
  ],
  entryComponents: [
    ParticipantsDeleteConfirmAlertComponent
  ]
})
export class ParticipantsModule {}

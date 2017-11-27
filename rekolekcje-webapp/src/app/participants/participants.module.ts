import { NgModule } from '@angular/core';
import { ParticipantsComponent } from './participants.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MockParticipantsService } from './mock-participants.service';
import { ParticipantDetailsComponent } from './participant-details/participant-details.component';
import { RouterModule } from '@angular/router';
import { ParticipantsAddNewComponent } from './participants-add-new/participants-add-new.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    RouterModule
  ],
  declarations: [
    ParticipantsComponent,
    ParticipantDetailsComponent,
    ParticipantsAddNewComponent
  ],
  providers: [
    MockParticipantsService
  ],
  exports: [
    ParticipantsComponent
  ]
})
export class ParticipantsModule {}

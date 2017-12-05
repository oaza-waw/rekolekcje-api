import { NgModule } from '@angular/core';
import { ParticipantsComponent } from './participants.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MockParticipantsService } from './mock-participants.service';
import { ParticipantDetailsComponent } from './details/participant-details.component';
import { ParticipantsAddNewComponent } from './add-new/participants-add-new.component';
import { SharedModule } from '../shared/shared.module';
import { ParticipantsListComponent } from './list/participants-list.component';
import { ParticipantsRoutingModule } from "./participants-routing.module";
import { ParticipantsDeleteAlertComponent } from './delete-alert/participants-delete-alert.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    ParticipantsRoutingModule
  ],
  declarations: [
    ParticipantsComponent,
    ParticipantDetailsComponent,
    ParticipantsAddNewComponent,
    ParticipantsListComponent,
    ParticipantsDeleteAlertComponent
  ],
  providers: [
    MockParticipantsService
  ],
  exports: [
    ParticipantsComponent
  ]
})
export class ParticipantsModule {}

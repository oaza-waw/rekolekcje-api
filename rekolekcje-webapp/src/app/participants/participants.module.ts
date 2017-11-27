import {NgModule} from "@angular/core";
import {ParticipantsComponent} from "./participants.component";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MockParticipantsService} from "./mock-participants.service";
import { ParticipantDetailsComponent } from './participant-details/participant-details.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
  ],
  declarations: [
    ParticipantsComponent,
    ParticipantDetailsComponent
  ],
  providers: [
    MockParticipantsService
  ],
  exports: [
    ParticipantsComponent
  ]
})
export class ParticipantsModule {}

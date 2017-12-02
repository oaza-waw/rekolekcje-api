import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParticipantsComponent } from "./participants.component";
import { ParticipantDetailsComponent } from "./details/participant-details.component";

const participantsRoutes: Routes = [
  { path: 'participants', component: ParticipantsComponent },
  { path: 'participants/details/:id', component: ParticipantDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(participantsRoutes)],
  exports: [RouterModule]
})
export class ParticipantsRoutingModule {
}

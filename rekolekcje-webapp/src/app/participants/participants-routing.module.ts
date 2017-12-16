import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParticipantsComponent } from './participants.component';
import { ParticipantDetailsComponent } from './details/participant-details.component';
import { ParticipantAddEditComponent } from './add-edit/participant-add-edit/participant-add-edit.component';

const participantsRoutes: Routes = [
  {
    path: 'participants',
    component: ParticipantsComponent,
    children: [
      {
        // @TODO is this really needed?
        path: 'add',
        component: ParticipantAddEditComponent
      }
    ]
  },
  {
    path: 'participants/:id',
    component: ParticipantDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(participantsRoutes)],
  exports: [RouterModule]
})
export class ParticipantsRoutingModule {
}

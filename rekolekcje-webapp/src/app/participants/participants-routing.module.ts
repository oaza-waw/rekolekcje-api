import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParticipantsComponent } from './participants.component';
import { ParticipantDetailsComponent } from './details/participant-details.component';
import { ParticipantAddEditComponent } from './add-edit/participant-add-edit/participant-add-edit.component';
import { AuthGuard } from '../auth/auth-guard.service';

const participantsRoutes: Routes = [
  {
    path: 'participants',
    component: ParticipantsComponent,
    canActivate: [AuthGuard],
    children: [
      {
        // @TODO is this really needed?
        path: 'add',
        component: ParticipantAddEditComponent,
        canActivate: [AuthGuard],
      }
    ]
  },
  {
    path: 'participants/:id',
    component: ParticipantDetailsComponent,
    canActivate: [AuthGuard],
  }
];

@NgModule({
  imports: [RouterModule.forChild(participantsRoutes)],
  exports: [RouterModule]
})
export class ParticipantsRoutingModule {
}

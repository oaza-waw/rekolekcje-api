import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParticipantsComponent } from "./participants.component";
import { ParticipantDetailsComponent } from "./details/participant-details.component";
import { CanDeactivateGuard } from '../can-deactivate-guard.service';
import { ParticipantsDeleteAlertComponent } from './delete-alert/participants-delete-alert.component';

const participantsRoutes: Routes = [
  {
    path: 'participants',
    component: ParticipantsComponent,
    children: [
      {
        path: 'details/:id',
        component: ParticipantDetailsComponent
      },
      {
        path: 'delete/:id',
        component: ParticipantsDeleteAlertComponent,
        outlet: 'popup'
      }
      // {
      //   path: 'delete/:id',
      //   component: ParticipantsComponent,
      //   canDeactivate: [CanDeactivateGuard]
      // },
    ]
  },
];

const participantsPopupRoutes: Routes = [
  {
    path: 'participants/delete/:id',
    component: ParticipantsDeleteAlertComponent,
    outlet: 'popup'
  }
];

@NgModule({
  imports: [RouterModule.forChild(participantsRoutes)],
  exports: [RouterModule]
})
export class ParticipantsRoutingModule {
}

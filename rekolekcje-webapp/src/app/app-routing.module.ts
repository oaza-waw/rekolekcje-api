import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {ParticipantsComponent} from "./participants/participants.component";
import {ParticipantDetailsComponent} from "./participants/participant-details/participant-details.component";

const routes: Routes = [
  { path: '', redirectTo: '/participants', pathMatch: 'full' },
  { path: 'participants', component: ParticipantsComponent },
  { path: 'participants/details/:id', component: ParticipantDetailsComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }

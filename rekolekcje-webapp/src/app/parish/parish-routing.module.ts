import { NgModule } from '@angular/core';
import { AuthGuard } from '../auth/auth-guard.service';
import { ParishComponent } from './parish.component';
import { RouterModule, Routes } from '@angular/router';

const parishRoutes: Routes = [
  {
    path: 'parish',
    component: ParishComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(parishRoutes)
  ],
  declarations: [],
  exports: [RouterModule]
})
export class ParishRoutingModule { }

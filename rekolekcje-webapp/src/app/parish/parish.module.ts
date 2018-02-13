import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParishComponent } from './parish.component';
import { ParishRoutingModule } from './parish-routing.module';
import { ParishListComponent } from './list/parish-list.component';

@NgModule({
  imports: [
    CommonModule,
    ParishRoutingModule
  ],
  declarations: [
    ParishComponent,
    ParishListComponent,
  ]
})
export class ParishModule { }

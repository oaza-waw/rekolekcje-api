import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { ParticipantsModule } from './participants/participants.module';
import { AppRoutingModule } from './app-routing.module';
import { NavigationModule } from './navigation/navigation.module';
import { DialogService } from './dialog.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ParticipantsModule,
    NavigationModule,
    AppRoutingModule,
  ],
  providers: [
    DialogService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

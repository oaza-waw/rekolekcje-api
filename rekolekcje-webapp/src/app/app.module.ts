import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { ParticipantsModule } from './participants/participants.module';
import { AppRoutingModule } from './app-routing.module';
import { NavigationModule } from './navigation/navigation.module';
import { ParticipantsRoutingModule } from './participants/participants-routing.module';
import { SharedModule } from './shared/shared.module';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { ParticipantsEffects } from './shared/store-shared/participants/participants-effects';
import { ParticipantsReducer } from './core/store/participants/participants-reducer';
import { AppReducer } from './core/store/app-store';

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
    ParticipantsRoutingModule,
    SharedModule,
    AppRoutingModule,
    /**
     * Store and effects imports
     */
    StoreModule.forRoot(AppReducer.reducer),
    StoreModule.forFeature('participantsModule', ParticipantsReducer.reducer),
    EffectsModule.forRoot([ParticipantsEffects]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

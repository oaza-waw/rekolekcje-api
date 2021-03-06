import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ParticipantsModule } from './participants/participants.module';
import { AppRoutingModule } from './app-routing.module';
import { NavigationModule } from './navigation/navigation.module';
import { SharedModule } from './shared/shared.module';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { ParticipantsEffects } from './participants/store/participants-effects';
import { ParticipantsReducer } from './participants/store/participants-reducer';
import { AppReducer } from './core/store/app-store';
import { AuthModule } from './auth/auth.module';
import { HomeModule } from './home/home.module';
import { ParishModule } from './parish/parish.module';
import { ParishReducer } from './parish/store/parish-reducer';
import { ParishEffects } from './parish/store/parish-effects';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NavigationModule,
    ParticipantsModule,
    AuthModule,
    HomeModule,
    ParishModule,
    SharedModule,
    AppRoutingModule,
    /**
     * Store and effects imports
     */
    StoreModule.forRoot(AppReducer.reducer),
    StoreModule.forFeature('participantsModule', ParticipantsReducer.reducer),
    StoreModule.forFeature('parishModule', ParishReducer.reducer),
    EffectsModule.forRoot([
      ParticipantsEffects,
      ParishEffects
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

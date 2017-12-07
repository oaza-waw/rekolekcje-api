import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthGuard } from './auth-guard.service';
import { AuthService } from './auth.service';
import { LoginRoutingModule } from './login-routing-module';
import { LoginComponent } from './login/login.component';
import { MatButtonModule, MatCardModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    LoginRoutingModule
  ],
  declarations: [
    LoginComponent
  ],
  providers: [
    AuthGuard,
    AuthService
  ],
  exports: []
})
export class AuthModule {
}

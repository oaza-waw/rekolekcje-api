import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthGuard } from './auth-guard.service';
import { AuthService } from './auth.service';
import { LoginRoutingModule } from './login-routing-module';
import { LoginComponent } from './login/login.component';
import {
  MatButtonModule, MatCardModule, MatFormFieldModule,
  MatInputModule
} from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AlreadyLoggedInGuard } from './already-logged-in-guard.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatCardModule,
    MatButtonModule,
    LoginRoutingModule
  ],
  declarations: [
    LoginComponent
  ],
  providers: [
    AuthGuard,
    AlreadyLoggedInGuard,
    AuthService
  ],
  exports: []
})
export class AuthModule {
}

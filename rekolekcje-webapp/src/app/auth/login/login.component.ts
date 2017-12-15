import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  message: string;

  form: FormGroup;
  @ViewChild('loginForm') ngFormDirective: NgForm;

  login = new FormControl('', Validators.required);
  password = new FormControl('', Validators.required);

  errorMessage: string;
  authorizationInProgress = false;

  constructor(public authService: AuthService, public router: Router, private fb: FormBuilder) {
    this.setMessage();
  }

  ngOnInit() {
    this.form = this.fb.group({
      login: this.login,
      password: this.password,
    });
    this.form.valueChanges.subscribe(() => delete this.errorMessage);
  }

  setMessage() {
    this.message = 'Logged ' + (this.authService.isAuthenticated() ? 'in' : 'out');
  }

  authorize() {
    this.message = 'Trying to log in...';
    if (!this.form.valid) {
      this.errorMessage = 'Fill out login and password fields';
      return null;
    }

    if (this.authorizationInProgress) {
      return null;
    } else {
      this.authorizationInProgress = true;
    }

    this.authService.login().subscribe(() => {
      this.setMessage();
      if (this.authService.isAuthenticated()) {
        let redirectUrl = this.authService.redirectUrl ? this.authService.redirectUrl : '/';
        this.router.navigate([redirectUrl]);
      }
    });
  }

  logout() {
    this.authService.logout();
    this.setMessage();
  }
}

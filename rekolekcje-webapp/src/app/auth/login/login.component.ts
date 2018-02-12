import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { debounceTime, distinctUntilChanged, takeUntil } from 'rxjs/operators';
import { Config } from '../../../config';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: [ './login.component.css' ]
})
export class LoginComponent implements OnInit {

  authorizationInProgress = false;
  errorMessage: string;
  form: FormGroup;
  message: string;

  @ViewChild('loginForm') ngFormDirective: NgForm;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(public authService: AuthService, public router: Router, private fb: FormBuilder) {
    this.setMessage();
  }

  ngOnInit() {
    this.form = this.fb.group({
      username: [ '', Validators.required ],
      password: [ '', Validators.required ]
    });

    this.form.valueChanges
      .pipe(
        distinctUntilChanged(),
        debounceTime(Config.inputDebounceTime),
        takeUntil(this.ngUnsubscribe)
      )
      .subscribe(text => delete this.errorMessage);
  }

  setMessage() {
    this.message = 'Logged ' + (this.authService.isAuthenticated() ? 'in' : 'out');
  }

  authorize() {
    this.message = 'Trying to log in...';
    if (!this.form.valid) {
      this.errorMessage = 'Fill out login and password fields';
      return;
    }

    if (this.authorizationInProgress) {
      return;
    } else {
      this.authorizationInProgress = true;
    }

    this.authService.authorize(this.form.value)
      .pipe(
        takeUntil(this.ngUnsubscribe)
      )
      .subscribe(() => {
        this.message = 'Yo! User logged in';
        console.log('Yo! User logged in');
        const redirectUrl = this.authService.redirectUrl ? this.authService.redirectUrl : '/';
        this.router.navigate([ redirectUrl ]);
        // this.router.navigate(['/'])
      });
    // .subscribe((result) => {
    //   if (result === true) {
    //     this.setMessage();
    //     let redirectUrl = this.authService.redirectUrl ? this.authService.redirectUrl : '/';
    //     this.router.navigate([redirectUrl]);
    //   } else {
    //     this.setMessage();
    //   }
    // if (this.authService.isAuthenticated()) {
    // }
    // });
  }

  logout() {
    this.authService.logout();
    this.setMessage();
  }
}

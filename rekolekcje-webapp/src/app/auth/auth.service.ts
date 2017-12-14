import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/delay'
import 'rxjs/add/operator/do'

@Injectable()
export class AuthService {
  private isLoggedIn = false;
  redirectUrl: string;

  login(): Observable<boolean> {
    return of(true)
      .delay(1000)
      .do(val => this.isLoggedIn = val);
  }

  logout(): void {
    this.isLoggedIn = false;
  }

  isAuthenticated(): boolean {
    return this.isLoggedIn;
  }
}

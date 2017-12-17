import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/delay'
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'
import 'rxjs/add/operator/map'
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operator/map';
// import { Headers, Http, Response } from '@angular/http';

export interface Credentials {
  username: string;
  password: string;
}

export type AuthState = 'authenticated' | 'anonymous' | 'unknown';


// export class Authentication {
//   state: AuthState;
//   username: string | null;
//
//   static unknown(): Authentication {
//     return new Authentication();
//   }
//
//   static anonymous(): Authentication {
//     return new Authentication({
//       state: 'anonymous',
//     });
//   }
//
//   static of(username: string) {
//     return new Authentication({
//       state: 'authenticated',
//       username
//     });
//   }
//
//   private constructor(options?: { state: AuthState;
//     username?: string;
//   }) {
//     this.state = options.state;
//     this.username = options.username;
//   }
//
//   isAuthenticated(): boolean {
//     return this.state === 'authenticated';
//   }
//
//   isKnown(): boolean {
//     return this.state !== 'unknown';
//   }
// }


@Injectable()
export class AuthService {
  private isLoggedIn = false;
  redirectUrl: string;

  // login(): Observable<boolean> {
  //   return of(true)
  //     .delay(1000)
  //     .do(val => this.isLoggedIn = val);
  // }

  logout(): void {
    this.isLoggedIn = false;
    localStorage.removeItem('currentUser');
  }

  isAuthenticated(): boolean {
    console.log('Checking if user is authenticated: ' + localStorage.getItem('currentUser'));
    return localStorage.getItem('currentUser') != null;
  }

  private readonly apiBaseUrl = '/api';
  private readonly authUrl = this.apiBaseUrl + '/auth';
  // private readonly authUrl = '/auth';

  // public authentication: Authentication;

  constructor(private http: HttpClient) {
    // this.authentication = Authentication.unknown();
  }

  authorize(credentials: Credentials) {
    // const body = JSON.stringify({
    //   username: credentials.username,
    //   password: credentials.password,
    // });
    const body = {username: credentials.username, password: credentials.password};

    // const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    // const options = new RequestOptions({ headers });
    // throw out current user details
    // this.updateAuthentication(Authentication.unknown());

    return this.http
      .post<Credentials>(this.authUrl, body)
      .map((response) => {
        // return true;
        console.log('Authentication response: ' + response);
        // let token = response.body && response.body.token;
        let token = response['token'];
        if (token) {
          console.log('token: ' + token);
          localStorage.setItem('currentUser', JSON.stringify({username: credentials.username, token: token}));
          return true;
        } else {
          return false;
        }
      });
      // .catch(err => {
      //   this.clearAuthentication();
      //   if (err.json && err.json() && err.json().errorMessage) {
      //     throw new Error(err.json().errorMessage);
      //   } else {
      //     throw new Error('An unknown error has occurred');
      //   }
      // });

      // .toPromise()
      // .then(response => {
      //   // the server should have set the authentication cookie and the response
      //   // should contain the user details
      //   const newAuth = this.processUserDetails(response.json());
      //   this.updateAuthentication(newAuth);
      //   return newAuth;
      // })
      // .catch(err => {
      //   this.clearAuthentication();
      //   if (err.json && err.json() && err.json().errorMessage) {
      //     throw new Error(err.json().errorMessage);
      //   } else {
      //     throw new Error('An unknown error has occurred');
      //   }
      // });
  }

  getToken(): String {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const token = currentUser && currentUser.token;
    return token ? token : '';
  }

  // clearAuthentication(): void {
  //   this.updateAuthentication(Authentication.anonymous());
  // }

  // private updateAuthentication(newValue: Authentication) {
  //   this.authentication = newValue;
  // }

}

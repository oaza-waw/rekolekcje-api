import { Injectable } from '@angular/core';
import 'rxjs/add/operator/delay'
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'
import 'rxjs/add/operator/map'
import { HttpClient } from '@angular/common/http';

export interface Credentials {
  username: string;
  password: string;
}

export type AuthState = 'authenticated' | 'anonymous' | 'unknown';


@Injectable()
export class AuthService {
  private isLoggedIn = false;
  redirectUrl: string;

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

  constructor(private http: HttpClient) {
  }

  authorize(credentials: Credentials) {
    const body = { username: credentials.username, password: credentials.password };

    return this.http
      .post<Credentials>(this.authUrl, body)
      .map((response) => {
        // console.log('Authentication response: ' + response);
        let token = response['token'];
        if (token) {
          // console.log('token: ' + token);
          localStorage.setItem('currentUser', JSON.stringify({
            username: credentials.username,
            token: token
          }));
          this.isLoggedIn = true;
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

  }

  getToken(): String {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const token = currentUser && currentUser.token;
    return token ? token : '';
  }

}

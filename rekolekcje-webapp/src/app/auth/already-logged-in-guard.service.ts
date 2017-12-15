import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable()
export class AlreadyLoggedInGuard implements CanActivate {

  constructor(private authService: AuthService,
              private router: Router,) {
  }

  canActivate(): boolean {
    let isAuthenticated = this.authService.isAuthenticated();

    if (isAuthenticated) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }

}

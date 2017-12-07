import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent {

  constructor(public authService: AuthService, private router: Router) {
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}

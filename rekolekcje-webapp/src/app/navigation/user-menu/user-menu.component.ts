import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {

  isAuthenticated: boolean;

  constructor(public authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.isAuthenticated = this.authService.isAuthenticated();
  }

  onLogout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}

import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Rekolekcje';

  constructor(public authService: AuthService) {}

  get userAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }
}

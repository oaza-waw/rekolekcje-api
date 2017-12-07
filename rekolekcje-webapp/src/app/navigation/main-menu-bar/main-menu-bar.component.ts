import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'main-menu-bar',
  templateUrl: './main-menu-bar.component.html',
  styleUrls: ['./main-menu-bar.component.css']
})
export class MainMenuBarComponent implements OnInit {

  constructor(authService: AuthService) { }

  ngOnInit(): void {
  }

}

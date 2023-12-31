import { ThisReceiver } from '@angular/compiler';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../AuthService';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthService) { }

  removeToken(): void {
    // localStorage.removeItem('token');
    // this.router.navigate(['/login'])
    this.authService.logout();
  }

}

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'student-forums-frontend';
  constructor(private router: Router, private authenticationService: AuthenticationService) {}
  ngOnInit() {
    if (!this.isLoggedIn()) {
      this.router.navigate([""])
    }
    else {
      this.router.navigate(["home"])
    }
  }
  isLoggedIn() {
    return this.authenticationService.isLoggedIn()
  }
  logout() {
    this.authenticationService.logout()
    this.router.navigate([""])

  }
}


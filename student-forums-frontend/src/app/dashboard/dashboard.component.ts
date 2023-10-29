import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { catchError, of, Observable } from 'rxjs';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  user:User|null;
  constructor(private router: Router, private authenticationService: AuthenticationService) {
    this.user=null;
  }
  ngOnInit() {
    if (this.isLoggedIn()) {
      this.user = JSON.parse(localStorage.getItem("user")!) as User
    }
    else {
    this.authenticationService.user.subscribe(u => {
      this.user = u
    })
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

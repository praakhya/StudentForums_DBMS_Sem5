import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { catchError, of, Observable } from 'rxjs';
import { Notification } from '../notification';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [DatePipe]
})
export class DashboardComponent {
  user:User|null;
  notifications:Array<Notification> = [];
  constructor(private router: Router, private authenticationService: AuthenticationService, private datePipe:DatePipe) {
    this.user=null;
  }
  ngOnInit() {
    this.authenticationService.getUser()?.subscribe(u => {
      this.user = u
      this.authenticationService.getNotifications(this.user!.id).subscribe(n => {
        this.notifications = n;
      })
    })
  }
  isLoggedIn() {
    return this.authenticationService.isLoggedIn()
  }
  logout() {
    this.authenticationService.logout()
    this.router.navigate([""])

  }

}

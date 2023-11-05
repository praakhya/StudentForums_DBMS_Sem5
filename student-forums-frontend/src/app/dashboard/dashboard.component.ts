import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { catchError, of, Observable } from 'rxjs';
import { Notification } from '../notification';
import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { PostCount } from '../postCount';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [DatePipe]
})

export class DashboardComponent {
  user:User|null;
  notifications:Array<Notification> = [];
  postCount: Number = 0;
  constructor(private router: Router, private authenticationService: AuthenticationService, private datePipe:DatePipe) {
    this.user=null;
  }
  ngOnInit() {
    this.authenticationService.getUser()?.pipe(catchError((error: HttpErrorResponse, caught: Observable<any>): Observable<any> => {
      if (error.status == 403) {
        console.error('Session Time Out', error);
        localStorage.clear();
        this.router.navigate([""])
      }
      else {
        console.log(error.name, error.message)
      }
      return of();
    })).subscribe(u => {
      this.user = u
      this.authenticationService.getNotifications(this.user!.id).subscribe(n => {
        console.log("Notifications: ",n)
        this.notifications = n;
      })
      this.authenticationService.getPostCount(this.user!.id).subscribe(num => {
        console.log("Number of posts: ",num, typeof(num))
        this.postCount = num.count
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

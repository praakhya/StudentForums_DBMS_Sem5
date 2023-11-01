import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { catchError, of, Observable } from 'rxjs';
import { User } from '../user';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  user?: any
  baseUrl: string = "/api";
  constructor(private http: HttpClient, private authenticationService: AuthenticationService, private router: Router) {
    this.user = this.authenticationService.user.getValue();
    //this.profileType="Student";
    console.log(this.user)
  }
  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem("user")!) as User
    if (!this.user)
    {
      this.authenticationService.user.subscribe(u => {
          this.user = u
          console.log(this.user)
        })
      }
  }
  uploadFile(event: any) {
    const file = event.target.files[0];
    console.log(event)
    this.authenticationService.uploadImage(file, "image").pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
      console.error('Session Time Out', error);
      localStorage.clear();
      return of();
    })).subscribe(pi => {
      console.log("profile image:",pi)
      this.authenticationService.setSession(localStorage.getItem("token")!)
      this.authenticationService.user.subscribe(u => {
        this.user = u
      })
    })
  }
  isLoggedIn() {
    return this.authenticationService.isLoggedIn()
  }



}
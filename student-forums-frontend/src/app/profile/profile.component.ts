import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { catchError, of, Observable } from 'rxjs';
import { User } from '../user';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  user?: any
  constructor(private authenticationService: AuthenticationService, private router: Router) {
    this.user = null;
    //this.profileType="Student";
    console.log(this.user)
  }
  ngOnInit() {
    if (this.authenticationService.isLoggedIn()) {
      this.user = JSON.parse(localStorage.getItem("user")!) as User
      
    } else {
      this.authenticationService.user.subscribe(u=> {
        this.user = u
      })
    }
  }
}
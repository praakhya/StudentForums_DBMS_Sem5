import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  user?: any
  constructor(private userService: UserService, protected loginService: LoginService, private router: Router,) {
    this.user = null;
    //this.profileType="Student";
    console.log(this.user)
  }
  ngOnInit() {
    if (this.loginService.isLoggedIn()) {
      this.userService.getUser()!.subscribe(u => {
        this.user = u;
        console.log(u)
        if (u) {
          this.user.imgUrl = `/api/user/image/${u.username}`;
        }
      })
    }
  }
}
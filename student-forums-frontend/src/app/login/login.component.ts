import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { LoginService } from '../login.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loggedIn:boolean;
  constructor(private authenticationService: AuthenticationService, private userService: UserService, private loginService: LoginService, private router: Router,) {
    this.loggedIn=this.loginService.isLoggedIn();
  }
  login(username:String, password:String) {
    console.log(username,password);
    this.authenticationService.setUserToken(username,password).subscribe(t => {
      this.authenticationService.setSession(t.token);
      console.log(t);
      this.loggedIn = this.loginService.isLoggedIn();
    });
  }
  logout() {
    this.authenticationService.logout()
    this.loggedIn = this.loginService.isLoggedIn();
  }

}

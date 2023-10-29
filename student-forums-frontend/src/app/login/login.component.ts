import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loggedIn:boolean;
  constructor(private authenticationService: AuthenticationService, private router: Router,) {
    this.loggedIn=this.authenticationService.isLoggedIn();
  }
  login(username:String, password:String) {
    console.log(username,password);
    this.authenticationService.setUserToken(username,password).subscribe(t => {
      this.authenticationService.setSession(t.token);
      console.log(t);
      this.loggedIn = this.authenticationService.isLoggedIn();
      this.router.navigate(["home"])
    });
  }
  logout() {
    this.authenticationService.logout()
    this.router.navigate(["login"]);
    this.loggedIn = this.authenticationService.isLoggedIn();
  }

}

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
  user?:any;
  loggedIn:boolean;
  constructor(private authenticationService: AuthenticationService, private userService: UserService, private loginService: LoginService, private router: Router,) {
    this.loggedIn=false;
  }
  ngOnInit() {
    this.loginService.getValue().subscribe(l => {
      this.loggedIn = l;
    })
  }
  login(username:String, password:String) {
    console.log(username,password);
    this.authenticationService.getUser(username).subscribe(u => {
      this.user = u;
      this.userService.setValue(this.user);
      console.log(u);
      this.loginService.setValue(true);
    });
  }
  logout() {
    this.loginService.setValue(false);

  }

}

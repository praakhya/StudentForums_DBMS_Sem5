import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user?:any
  constructor(private authenticationService: AuthenticationService, private router: Router,) {
  }
  login(username:String, password:String) {
    console.log(username,password);
    this.authenticationService.getUser(username).subscribe(u => {
      this.user = u;
      console.log(u)
    });
  }

}

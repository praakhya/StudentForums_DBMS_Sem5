import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  signUp:boolean;
  constructor(private authenticationService: AuthenticationService) {
    this.signUp=false
  }
  isLoggedIn(){
    return this.authenticationService.isLoggedIn()
  }
  setSignUp(){
    this.signUp=true
  }
  unsetSignUp(){
    this.signUp=false
  }
}

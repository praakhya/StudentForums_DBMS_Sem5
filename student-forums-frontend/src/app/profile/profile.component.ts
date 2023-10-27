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
  profileImgSrc:String;
  profileUsername:String;
  profileEmail:String;
  profileName: String;
  profileContact?: String;
  //profileType: String;
  user?:any
  constructor(private authenticationService: AuthenticationService, private userService: UserService, private loginService:LoginService, private router: Router,) {
    this.user = this.userService.getValue()
    this.profileImgSrc= "./assets/dummyProfilePicture.webp";
    this.profileUsername="";
    this.profileEmail="";
    this.profileName="";
    this.profileContact="";
    //this.profileType="Student";
    console.log(this.user)
  }
  ngOnInit() {
    this.loginService.getValue().subscribe(loggedIn => {
      if (loggedIn) {
        this.userService.getValue()!.subscribe(u => {
          this.user = u;
          console.log(u)
          if (u) {
            this.profileUsername=u.username;
            this.profileEmail=u.email;
            this.profileName=u.name;
            this.profileContact=u.contact;
          }
        });
      }
    })
    
  }


}

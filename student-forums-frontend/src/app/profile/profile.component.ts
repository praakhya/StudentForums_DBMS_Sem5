import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
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
  profileContact: String;
  profileType: String;
  constructor(private authenticationService: AuthenticationService, private router: Router,) {
    
    this.profileImgSrc= "./assets/dummyProfilePicture.webp";
    this.profileUsername="MyUsername";
    this.profileEmail="myself@example.com";
    this.profileName="My Name";
    this.profileContact="+911234567890";
    this.profileType="Student";
  }

}

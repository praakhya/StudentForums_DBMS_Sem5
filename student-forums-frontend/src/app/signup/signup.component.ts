import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { LoginService } from '../login.service';
import { User } from '../user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  error:String;
  role?: string;
  departments:string[] =  [
    "Computer Science and Engineering",
    "Electronics and Communication Engineering",
    "Electrical and Electronics Engineering",
    "Artificial Intelligence and Machine Learning",
    "Mechanical Engineering",
    "Civil Engineering"
  ];
  constructor(private authenticationService:AuthenticationService, private loginService:LoginService) {
    this.error = "";
    
  }
  signup(username:String, email:String, name:String, password:String, confirmPassword:String, role:String) {
    if (password==confirmPassword) {
      var user = new User(username=username,password=password,email=email,name=name,role=role);
      this.authenticationService.postUser(user).subscribe(res => {
        console.log(res);
        alert(res)
      })
    }
    else {
      this.error="Passwords do not match";
    }


  }

}

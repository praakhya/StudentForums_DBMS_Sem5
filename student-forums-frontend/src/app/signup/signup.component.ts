import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { Forum } from '../forum';
import { catchError, of, Observable } from 'rxjs';
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
  constructor(private authenticationService:AuthenticationService) {
    this.error = "";
    
  }
  signup(username:string, email:string, name:string, password:string, confirmPassword:string, role:string, department:string) {
    if (password==confirmPassword) {
      var user = new User(username=username,password=password,email=email,name=name,role=role, department=department, "./assets/dummyProfilePicture.webp");
      this.authenticationService.postUser(user).pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
        console.error('Session Time Out', error);
        localStorage.clear();
        return of();
      })).subscribe(res => {
        console.log(res);
        alert(res)
      })
    }
    else {
      this.error="Passwords do not match";
    }


  }

}

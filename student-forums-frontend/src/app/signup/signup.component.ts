import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { Forum } from '../forum';
import { catchError, of, Observable } from 'rxjs';
import { Faculty } from '../faculty';
import { Student } from '../student';
import { Router } from '@angular/router';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  error:String;
  role?: string;
  rollNo: string = "";
  jobTitle: string = "";
  departments:string[] =  [
    "Computer Science and Engineering",
    "Electronics and Communication Engineering",
    "Electrical and Electronics Engineering",
    "Artificial Intelligence and Machine Learning",
    "Mechanical Engineering",
    "Civil Engineering"
  ];
  constructor(private authenticationService:AuthenticationService, private router:Router) {
    this.error = "";
    
  }
  signup(username:string, email:string, name:string, password:string, confirmPassword:string, role:string, department:string, rollNo:string, jobTitle:string) {
    if (password==confirmPassword) {
      var user = null;
      if (role=="faculty") {
        user = new Faculty(null, username, password, email, name, null, jobTitle, department, [], [], null);
        this.authenticationService.postFaculty(user!).pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
          console.error('Session Time Out', error);
          localStorage.clear();
          return of();
        })).subscribe(res => {
          console.log(res);
          alert("User successfully signed up. Log in to continue")
          this.router.navigate([""])
        })
      }
      else {
        user = new Student(null, username, password, email, name, null, null, rollNo, department, null, [], [], []);
        this.authenticationService.postStudent(user!).pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
          console.error('Session Time Out', error);
          localStorage.clear();
          return of();
        })).subscribe(res => {
          console.log(res);
          
          alert(res)
        })
      }
      
    }
    else {
      this.error="Passwords do not match";
    }


  }

}

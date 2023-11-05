import { Component, ViewChild } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { Forum } from '../forum';
import { catchError, of, Observable, BehaviorSubject } from 'rxjs';
import { Faculty } from '../faculty';
import { Student } from '../student';
import { Router } from '@angular/router';
import { ElementRef } from '@angular/core';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  facultyError: BehaviorSubject<boolean> = new BehaviorSubject(false);
  studentError: BehaviorSubject<boolean> = new BehaviorSubject(false);
  passwordError: BehaviorSubject<boolean> = new BehaviorSubject(false);
  

  error: String;
  role?: string;
  departments: string[] = [
    "Computer Science and Engineering",
    "Electronics and Communication Engineering",
    "Electrical and Electronics Engineering",
    "Artificial Intelligence and Machine Learning",
    "Mechanical Engineering",
    "Civil Engineering"
  ];
  constructor(private authenticationService: AuthenticationService, private router: Router) {
    this.error = "";

  }


  signup(username: string, email: string, name: string, password: string, confirmPassword: string, role: string, department: string, rollNo: string, jobTitle: string) {
    if ((password == confirmPassword) && (username && email && name && password && confirmPassword)) {
      var user = null;
      this.passwordError.next(false)
      if (role == "faculty") {
        if (department && jobTitle) {
          this.facultyError.next(false)
          user = new Faculty(null, username, password, email, name, null, jobTitle, department, [], [], null);
          console.log("faculty in signup: ", user)
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
          this.facultyError.next(true)
        }
      }
      else {
        if (rollNo && department) {
          this.studentError.next(false)
          user = new Student(null, username, password, email, name, null, null, rollNo, department, null, [], [], []);
          console.log("student in signup: ", user)
          this.authenticationService.postStudent(user!).pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
            console.error('Session Time Out', error);
            localStorage.clear();
            return of();
          })).subscribe(res => {
            console.log(res);

            alert(res)
          })
        }
        else {
          this.studentError.next(true)
        }
      }

    }
    else {
      this.passwordError.next(true)
    }


  }

}

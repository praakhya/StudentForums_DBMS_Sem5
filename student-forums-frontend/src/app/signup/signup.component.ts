import { Component, ViewChild } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { Forum } from '../forum';
import { catchError, of, Observable, BehaviorSubject } from 'rxjs';
import { Faculty } from '../faculty';
import { Student } from '../student';
import { Router } from '@angular/router';
import { ElementRef } from '@angular/core';
import { SnackbarService } from '../snackbar.service';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  facultyError:string = "Faculty details were not filled properly"
  studentError:string = "Student details were not filled properly"
  passwordError:string = "Passwords did not match"
  

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
  constructor(private authenticationService: AuthenticationService, private router: Router, private snackBarService: SnackbarService) {
    this.error = "";

  }


  signup(username: string, email: string, name: string, password: string, confirmPassword: string, role: string, department: string, rollNo: string, jobTitle: string) {
    if ((password == confirmPassword)) {
      if (username && email && name && password && confirmPassword) {
      var user = null;
      if (role == "faculty") {
        if (department && jobTitle) {
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
          this.snackBarService.show(this.facultyError)
        }
      }
      else {
        if (rollNo && department) {
          user = new Student(null, username, password, email, name, null, null, rollNo, department, null, [], [], []);
          console.log("student in signup: ", user)
          this.authenticationService.postStudent(user!).pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
            console.error('Session Time Out', error);
            localStorage.clear();
            return of();
          })).subscribe(res => {
            console.log(res);
            alert("User successfully signed up. Log in to continue")
          })
        }
        else {
          this.snackBarService.show(this.studentError)
        }
      }
    }
    else {
      this.snackBarService.show("Fill all required details")
    }

    }
    else {
      this.snackBarService.show(this.passwordError)
    }


  }

}

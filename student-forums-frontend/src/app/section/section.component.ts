import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { Router } from '@angular/router';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import { of, catchError, Observable } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css'],
})
export class SectionComponent {
  user: User | null = null;
  semesters = ['1','2','3','4','5','6','7','8']
  sections = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L']
  constructor(private authenticationService: AuthenticationService, private router:Router) {
  }
  ngOnInit() {
    if (this.authenticationService.isLoggedIn()) {
      this.authenticationService.getUser()?.subscribe(u => {
        this.user = u;
      })
      
    }
    else {
      this.router.navigate([""])
    }
  }
  isFaculty() {
    this.authenticationService.isFaculty()
  }
  addSection(semester:string,section:string, repUsername:string, teacherUsername:string) {
    if (!semester || !section || !repUsername || !teacherUsername) {
      console.log("Please fill entries")
      return
    }
    var date = new Date()
    var name = semester+section+" "+date.getFullYear().toString()
    console.log(name)
    this.authenticationService.createSection(name, repUsername, teacherUsername).pipe(catchError((error: HttpErrorResponse, caught: Observable<any>): Observable<any> => {
      if (error.status == 404) {
        console.log("The username of student or teacher was not found")
      }
      if (error.status == 403) {
      console.error('Session Time Out', error);
      localStorage.clear();
    }
      return of();
      
    })).subscribe(s => {
      console.log("created section: ",s)
    })
  }


}

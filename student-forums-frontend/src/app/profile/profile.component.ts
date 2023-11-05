import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { catchError, of, Observable } from 'rxjs';
import { User } from '../user';
import { HttpClient } from '@angular/common/http';
import { Student } from '../student';
import { Faculty } from '../faculty';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  user: User|null = null
  studentUser: Student|null = null;
  facultyUser: Faculty|null = null
  baseUrl: string = "/api";
  editMode: boolean = false;
  inputMemberships: Array<string> = [];
  inputPublications: Array<string> = [];
  inputSkills: Array<string> = [];
  inputDomains: Array<string> = []
  constructor(private http: HttpClient, private authenticationService: AuthenticationService, private router: Router) {
    //this.profileType="Student";
    console.log(this.user)
  }
  ngOnInit() {
      this.authenticationService.getUser()?.subscribe(u => {
          this.user = u
          console.log("profile of user: ",this.user)
          if (this.user.role=="FACULTY") {
            this.authenticationService.getFaculty(this.user.username).subscribe(f => {
              this.facultyUser = f
            });
          }
          else {
            this.authenticationService.getStudent(this.user.username).subscribe(s => {
              this.studentUser = s
              this.inputMemberships = s.memberships
              this.inputPublications = s.publications
              this.inputSkills = s.skills
            });
          }
        })
        console.log("faculty:",this.facultyUser, "student:",this.studentUser, "user:",this.user)
  }
  uploadFile(event: any) {
    const file = event.target.files[0];
    console.log(event)
    this.authenticationService.uploadImage(file, "image").pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
      console.error('Session Time Out', error);
      localStorage.clear();
      return of();
    })).subscribe(pi => {
      console.log("profile image:",pi)
      this.authenticationService.setSession(localStorage.getItem("token")!)
      this.authenticationService.user.subscribe(u => {
        this.user = u
      })
    })
  }
  isLoggedIn() {
    return this.authenticationService.isLoggedIn()
  }
  enableEdit() {
    this.editMode = true
  }
  isFaculty() {
    return this.user!.role == "FACULTY"
  }
  isStudent() {
    return this.user!.role == "STUDENT"
  }
  disableEdit(email:string, fullname:string, contact:string) {
    if (this.user?.role == "FACULTY") {
      var faculty = new Faculty(
        this.facultyUser!.id,
        this.facultyUser!.username,
        this.facultyUser!.password, 
        email,
        fullname,
        null,
        null,
        this.facultyUser?.department!,
        this.inputDomains,
        this.inputPublications,
        contact);
      this.authenticationService.updateFacultyProfile(faculty).subscribe(f => {
        console.log("Updated faculty: ",f)
        this.authenticationService.getUser()?.subscribe(u => {
          this.user = u;
        })
      })
    }
    if (this.user?.role == "STUDENT") {
      var student = new Student(
        this.studentUser!.id,
        this.studentUser!.username,
        this.studentUser!.password,
        email,
        fullname,
        null,
        contact,
        this.studentUser?.rollNo!,
        this.studentUser?.department!,
        null,
        this.inputMemberships,
        this.inputPublications,
        this.inputSkills
      )
      this.authenticationService.updateStudentProfile(student).subscribe(s => {
        console.log("Updated student: ", s)
        this.authenticationService.getUser()?.subscribe(u => {
          this.user = u;
        })
      })
    }
    
    this.editMode = false
  }
  addToList(item:string, arrayToAppend:Array<string>) {
    arrayToAppend.push(item)
  }
  deleteFromList(item:string, arrayToAppend:Array<string>) {
    arrayToAppend = arrayToAppend.filter(arrayItem => {return arrayItem!==item})
    console.log("array:",arrayToAppend)

  }
  deleteFromMembership(item:string) {
    this.inputMemberships = this.inputMemberships.filter(arrayItem => {return arrayItem!==item})
    console.log("array:",this.inputMemberships)

  }
  deleteFromPublication(item:string) {
    this.inputPublications = this.inputPublications.filter(arrayItem => {return arrayItem!==item})
    console.log("array:",this.inputPublications)

  }
  deleteFromSkills(item:string) {
    this.inputSkills = this.inputSkills.filter(arrayItem => {return arrayItem!==item})
    console.log("array:",this.inputSkills)

  }
  deleteFromDomains(item:string) {
    this.inputDomains = this.inputDomains.filter(arrayItem => {return arrayItem!==item})
    console.log("array:",this.inputDomains)

  }



}
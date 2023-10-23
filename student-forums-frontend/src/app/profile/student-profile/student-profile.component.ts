import { Component } from '@angular/core';

@Component({
  selector: 'app-student-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css']
})
export class StudentProfileComponent {
  studentRollno: String;
  studentDepartment: String;
  studentClass: String;
  constructor() {
    this.studentRollno = "PES1UG21CSXYZ";
    this.studentDepartment = "Computer Science";
    this.studentClass = "XY";
  }

}

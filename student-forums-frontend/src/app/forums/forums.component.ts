import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Forum } from '../forum';
import { Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { User } from '../user';
@Component({
  selector: 'app-forums',
  templateUrl: './forums.component.html',
  styleUrls: ['./forums.component.css']
})
export class ForumsComponent {
  forums: Array<Forum>;
  createMode: boolean;
  forumName = '';
  user: User|null; 
  constructor(private authenticationService: AuthenticationService, private router:Router) {
    this.forums = [];
    this.createMode = false;
    this.user = null;
  }
  ngOnInit() {
    if (this.isLoggedIn()) {
      this.user = JSON.parse(localStorage.getItem("user")!) as User
      if (this.user)
        this.forums = this.user.forums
    }
    else {
      this.authenticationService.user.subscribe(u => {
        if (u) {
          this.forums = u.forums
        }
      })
    }
    
  }
  openForum(id:string) {
    console.log("launching forum ",id)
    this.router.navigate(["/forums",id])
  }
  toggleCreate() {
    this.createMode = !this.createMode;
  }
  userIsFaculty(){
    var user = JSON.parse(localStorage.getItem("user")!) as User;
    if (user) {
      return user.role=="FACULTY"
    }
    return false
  }
  isLoggedIn() {
    return this.authenticationService.isLoggedIn()
  }
  createForum() {
    this.authenticationService.postForum(this.forumName, this.user!.id).pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
      console.error('Session Time Out', error);
      localStorage.clear();
      return of();
    })).subscribe(f => {
      this.forums.push(f)
      this.authenticationService.setSession(localStorage.getItem("token")!);
    })
  }

}

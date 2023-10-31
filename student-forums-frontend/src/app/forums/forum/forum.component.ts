import { Component, Input } from '@angular/core';
import { AuthenticationService } from 'src/app/authentication.service';
import { Forum } from 'src/app/forum';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of, Observable } from 'rxjs';
import { Dialog } from '@angular/cdk/dialog';
import { User } from 'src/app/user';
@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})

export class ForumComponent {
  forum: Forum|null;
  id:string;
  constructor(private authenticationService:AuthenticationService, private router:Router, private activatedRoute:ActivatedRoute, public dialog: Dialog) {
    this.id = ""
    this.forum = null;
  }
  ngOnInit() {
    this.getForum()
  }
  getForum() {
    this.id = this.activatedRoute.snapshot.params["id"];
    console.log("forum received: ", this.id)
    this.authenticationService.getForum(this.id)?.pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
      console.error('Session Time Out', error);
      localStorage.clear();
      this.router.navigate([""]);
      return of();
    })).subscribe(f => {
      console.log("forum component: ",f)
      this.forum = f
    })
  }
  navigateToForumHome() {
    this.router.navigate(["forums"])
  }
  addUserToForum(username:string) {
    console.log("username: ",username)
    this.authenticationService.subscribeToForum(username,this.forum?.id!).pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
      console.error('Session Time Out', error);
      localStorage.clear();
      this.router.navigate([""]);
      return of();
    })).subscribe(f => {
      console.log("forum subscription: ",f, f.users)
      this.forum = f
    })
  }
  openEditor(){
    var user = JSON.parse(localStorage.getItem("user")!) as User
    this.router.navigate(["create-post",this.forum?.id, user.username])
/*    this.dialog.open(CreatePostComponent, {
      data: {
        forumId: this.forum?.id,
        poster:JSON.parse(localStorage.getItem("user")!) as User
      },
    });*/
  }
  
 
}

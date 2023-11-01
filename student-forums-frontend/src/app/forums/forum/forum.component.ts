import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AuthenticationService } from 'src/app/authentication.service';
import { Forum } from 'src/app/forum';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of, Observable, BehaviorSubject } from 'rxjs';
import { Dialog } from '@angular/cdk/dialog';
import { User } from 'src/app/user';
import { Post } from 'src/app/post';
import { UIService } from 'src/app/ui.service';
@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})

export class ForumComponent {
  forum: Forum|null;
  posts: Array<Post>;
  id:string;
  currentForumSetting:boolean = false;
  constructor(private authenticationService:AuthenticationService, private router:Router, private activatedRoute:ActivatedRoute, public dialog: Dialog, private uiService: UIService) {
    this.id = ""
    this.forum = null;
    this.posts = [];
  }
  ngOnInit() {
    this.getForum()
    this.authenticationService.posts.subscribe(p => {
      this.posts = p
    })
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
      this.authenticationService.getPosts(this.id).pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
        console.error('Session Time Out', error);
        localStorage.clear();
        this.router.navigate([""]);
        return of();
      })).subscribe(p => {
        this.authenticationService.posts.next(p);
        this.posts = p;
      })
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
  isFaculty() {
    var user = JSON.parse(localStorage.getItem("user")!) as User
    console.log("role: ",user.role)
    return user.role=="FACULTY"
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
  editForum() {
    this.currentForumSetting = !this.currentForumSetting
    this.uiService.forumSettingMode.next(this.currentForumSetting)
  }
  
 
}

import { Component, Input } from '@angular/core';
import { Post } from '../post';
import { AuthenticationService } from '../authentication.service';
import { MatDialog } from '@angular/material/dialog';
import { User } from '../user';
import { BehaviorSubject } from 'rxjs';
import { UIService } from '../ui.service';
import { Resource } from '../resource';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
  @Input() post: Post|null = null;
  @Input() forumId: string = "";
  @Input() forumSetting: boolean = false;
  postedResources: Array<Resource> = []

  constructor(private authenticationService: AuthenticationService, private uiService:UIService, private router:Router) {};
  ngOnInit() {
    console.log("post",this.post)
    this.uiService.forumSettingMode.subscribe(s => {
      this.forumSetting = s
    })
    for (var resId of this.post?.resourceIds!) {
      this.authenticationService.getResource(resId).subscribe(r => {
        this.postedResources.push(r);
      })
    }

    
  }
  addPostReply(reply:string) {
    this.authenticationService.createPost(this.forumId, reply, "Reply ", `Reply to ${this.post?.title}`, this.post?.id!,[]).subscribe(p => {
      console.log("post:",p)
      this.authenticationService.reloadPosts(this.forumId)
    })
  }
  isFaculty() {
    var user = JSON.parse(localStorage.getItem("user")!) as User
    console.log("role: ",user.role)
    return user.role=="FACULTY"
  }
  deletePost() {
    this.authenticationService.deletePost(this.post?.id!, this.forumId).subscribe(p => {
      console.log("deleted post: ", p);
      this.authenticationService.getPosts(this.forumId).subscribe(p => {
        this.authenticationService.posts.next(p);
      })
      
    })
  }
  download(url:string) {
      this.router.navigate([]).then(result => window.open(url, "_blank"));
  }
  addToMyFile(file:any, multiPartVar:string) {}
}

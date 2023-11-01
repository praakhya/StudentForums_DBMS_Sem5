import { Component, Input } from '@angular/core';
import { Post } from '../post';
import { AuthenticationService } from '../authentication.service';
import { MatDialog } from '@angular/material/dialog';
import { User } from '../user';
import { BehaviorSubject } from 'rxjs';
import { UIService } from '../ui.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
  @Input() post: Post|null = null;
  @Input() forumId: string = "";
  @Input() forumSetting: boolean = false;


  constructor(private authenticationService: AuthenticationService, private uiService:UIService) {};
  ngOnInit() {
    console.log("post",this.post)
    this.uiService.forumSettingMode.subscribe(s => {
      this.forumSetting = s
    })
    
  }
  addPostReply(reply:string) {
    this.authenticationService.createPost(this.forumId, reply, "Reply ", `Reply to ${this.post?.title}`, this.post?.id!).subscribe(p => {
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
}

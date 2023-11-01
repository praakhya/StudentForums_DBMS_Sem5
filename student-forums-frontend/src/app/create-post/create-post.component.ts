import { Component, Inject, Input } from '@angular/core';
import { DIALOG_DATA, DialogModule, DialogRef } from '@angular/cdk/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent {
  dataModel: string = "";
  forumId!:string;
  username!:string;
  postTypes:Array<string> = ["Announcement", "Information", "Urgent"]
  constructor(private router:Router, private activatedRoute:ActivatedRoute, private authenticationService: AuthenticationService) {
    
  }
  ngOnInit() {
    this.forumId = this.activatedRoute.snapshot.params["forumId"];
  }
  createPost(title:string, type:string) {
    //this.authenticationService.createPost(this.forumId, this.userId, this.dataModel, "Announcement", )
    console.log("title:", title);
    console.log("type:", type);
    console.log("content:",this.dataModel);
    this.authenticationService.createPost(this.forumId, this.dataModel, type, title, null).subscribe(p => {
      console.log("new post: ",p)
      this.router.navigate(["forums/"+this.forumId])
    });
  }
  goToForum() {
    this.router.navigate(["forums/"+this.forumId])
  }
}

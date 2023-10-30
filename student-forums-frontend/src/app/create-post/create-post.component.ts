import { Component, Inject, Input } from '@angular/core';
import { DIALOG_DATA, DialogModule, DialogRef } from '@angular/cdk/dialog';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent {
  dataModel: string = "";
  forumId!:string;
  userId!:string;
  constructor(private router:Router, private activatedRoute:ActivatedRoute) {

  }
  ngOnInit() {
    this.forumId = this.activatedRoute.snapshot.params["forumId"];
    this.userId = this.activatedRoute.snapshot.params["userId"];
  }
  showPost() {

    console.log(this.dataModel);
  }
  goToForum() {
    this.router.navigate(["forums/"+this.forumId])
  }
}

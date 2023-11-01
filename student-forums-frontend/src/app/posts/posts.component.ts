import { Component, EventEmitter, Input } from '@angular/core';
import { Post } from '../post';
import { BehaviorSubject } from 'rxjs';
import { AuthenticationService } from '../authentication.service';
@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent {
  @Input() posts: Array<Post> = []
  @Input() forumId: string = ""
  constructor(private authenticationService: AuthenticationService) {}
  ngOnInit() {
    console.log("all posts: ",this.posts)
    this.authenticationService.posts.subscribe(p=>{
      this.posts = p
    })
  }

}

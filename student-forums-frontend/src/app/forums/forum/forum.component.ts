import { Component, Input } from '@angular/core';
import { AuthenticationService } from 'src/app/authentication.service';
import { Forum } from 'src/app/forum';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of, Observable } from 'rxjs';
@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent {
  forum: Forum|null;
  id:string;
  constructor(private authenticationService:AuthenticationService, private router:Router, private activatedRoute:ActivatedRoute) {
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
      console.log(f)
      this.forum = f
    })
  }

}

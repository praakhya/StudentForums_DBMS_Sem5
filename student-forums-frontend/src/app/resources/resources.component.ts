import { Component } from '@angular/core';
import { FileHandle } from '../dragDrop.directive';
import { AuthenticationService } from '../authentication.service';
import { catchError, Observable, of } from 'rxjs';
import { Resource } from '../resource';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.css'],
  providers: [DatePipe]
})
export class ResourcesComponent {
  name = 'Angular 5';
  files: FileHandle[] = [];
  resources: Array<Resource>;
  constructor(private authenticationService: AuthenticationService, private router:Router) {
    this.resources = []
  }
  ngOnInit() {
    this.authenticationService.getResources().subscribe(r => {
      this.resources = r
      console.log("date: ",r.map(ri => ri.dateOfPublish))
    })
  }
  
  filesDropped(files: FileHandle[]): void {
    this.files = files;
  }
  onFileSelected(event: any) {
    var plainFiles = event.target.files;
    var fh:FileHandle= {
      file: event.target.files[0],
      url:""
    }
    this.files.push(fh)
    
  }
  uploadFile() {
    const file = this.files[0]
    this.authenticationService.uploadFile(file, 'uploadedFile').pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
      console.error('Session Time Out', error.status, error.error);
      localStorage.clear();
      return of();
    })).subscribe(f => {
      console.log("file:",f)
      this.resources.push(f);
      this.files = []
      })
  }
  downloadFile(url:string) {
    this.router.navigate([]).then(result => window.open(url, "_blank"));
  }
  deleteFile(id:string) {
    this.authenticationService.deleteResource(id).subscribe(delr => {
      console.log("deleted resource: ",delr)
      this.authenticationService.getResources().subscribe(r => {
        this.resources = r
      })
    })

  }
}

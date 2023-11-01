import { Component } from '@angular/core';
import { FileHandle } from '../dragDrop.directive';
import { AuthenticationService } from '../authentication.service';
import { catchError, Observable, of } from 'rxjs';
import { Resource } from '../resource';
@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.css']
})
export class ResourcesComponent {
  name = 'Angular 5';
  files: FileHandle[] = [];
  resources: Array<Resource>;
  constructor(private authenticationService: AuthenticationService) {
    this.resources = []
  }
  ngOnInit() {
    this.authenticationService.getResources().subscribe(r => {
      this.resources = r
    })
  }

  filesDropped(files: FileHandle[]): void {
    this.files = files;
  }
  onFileSelected(event: any) {
    this.files = [event.target.files[0]]
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
      })
  }
}

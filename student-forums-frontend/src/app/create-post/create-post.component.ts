import { Component, Inject, Input, ViewChild } from '@angular/core';
import { DIALOG_DATA, DialogModule, DialogRef } from '@angular/cdk/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { Resource } from '../resource';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import {SelectionModel} from '@angular/cdk/collections';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css'],
})
export class CreatePostComponent {
  dataModel: string = "";
  forumId!:string;
  username!:string;
  postTypes:Array<string> = ["Announcement", "Information", "Urgent"]
  resources:Array<Resource> = []
  displayedColumns: string[] = ['select', 'name', 'contentType', 'ownerName'];
  dataSource = new MatTableDataSource(this.resources)
  selection = new SelectionModel<Resource>(true, []);
  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  constructor(private router:Router, private activatedRoute:ActivatedRoute, private authenticationService: AuthenticationService) {
    
  }
  ngOnInit() {
    this.forumId = this.activatedRoute.snapshot.params["forumId"];
    this.authenticationService.getResources().subscribe(res => {
      this.resources = res;
    })
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator!;
    this.dataSource.sort = this.sort!;
  }
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }
  toggleAllRows() {
    if (this.isAllSelected()) {
      this.selection.clear();
      return;
    }

    this.selection.select(...this.dataSource.data);
  }
  checkboxLabel(row?: Resource): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.id + 1}`;
  }
  createPost(title:string, type:string) {
    //this.authenticationService.createPost(this.forumId, this.userId, this.dataModel, "Announcement", )
    console.log("title:", title);
    console.log("type:", type);
    console.log("content:",this.dataModel);
    var selectResources = [];
    for (var res of this.resources) {
      if (this.selection.isSelected(res)) {
        selectResources.push(res.id)
      }
    }
    this.authenticationService.createPost(this.forumId, this.dataModel, type, title, null, selectResources).subscribe(p => {
      console.log("new post: ",p)
      this.router.navigate(["forums/"+this.forumId])
    });
  }
  goToForum() {
    this.router.navigate(["forums/"+this.forumId])
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}

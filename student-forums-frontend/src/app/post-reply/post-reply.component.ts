import { Component, Input } from '@angular/core';
import {NestedTreeControl} from '@angular/cdk/tree';
import { MatTreeNestedDataSource } from '@angular/material/tree';
import { Post } from '../post';
interface ExampleFlatNode {
  expandable: boolean;
  title: string;
  content: string;
  posterName: string;
  level: number;
}
@Component({
  selector: 'app-post-reply',
  templateUrl: './post-reply.component.html',
  styleUrls: ['./post-reply.component.css']
})

export class PostReplyComponent {
  @Input() posts?: Post[]|null = null;
  @Input() forumId: string = "";

  treeControl = new NestedTreeControl<Post>(post => post.posts);
  dataSource = new MatTreeNestedDataSource<Post>();

  constructor() {
  }
  ngOnInit() {
    console.log("reply posts ", this.posts, this.posts==null, !this.posts, this.posts?.length)
    if (this.posts != null) {

      this.dataSource.data = this.posts;
    }
  }


  hasChild = (_: number, post: Post) => !!post.posts && post.posts.length > 0;
  /*private _transformer = (node: Post, level: number) => {
    return {
      expandable: !!node.posts && node.posts.length > 0,
      title: node.title,
      content: node.content,
      posterName: node.posterName,
      level: level,
    };
  };

  treeControl = new FlatTreeControl<ExampleFlatNode>(
    node => node.level,
    node => node.expandable,
  );

  treeFlattener = new MatTreeFlattener(
    this._transformer,
    node => node.level,
    node => node.expandable,
    node => node.posts,
  );

  dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

  constructor() {
    if (this.posts != null) {

      this.dataSource.data = this.posts;
    }
  }

  hasChild = (_: number, node: ExampleFlatNode) => node.expandable;*/

}

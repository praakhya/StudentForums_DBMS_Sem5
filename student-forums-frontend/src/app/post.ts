export class Post {
    id:string;
    type:string;
    title:string;
    content:string;
    posterId:string;
    posterName:string="";
    posterImgUrl:string="";
    parentId:string|null;
    posts:Array<Post>;
    resources:null = null;
    constructor(
        id:string,
        type:string,
        title:string,
        content:string,
        posterId:string,
        parentId:string|null,
        posts:Array<Post>,
    ) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.posterId = posterId;
        this.parentId = parentId;
        this.posts = posts;
        

    }
}

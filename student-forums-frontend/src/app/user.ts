import { Forum } from "./forum";

export class User {
    id:string;
    username:string;
    password:string;
    email:string;
    name:string;
    imgUrl:string;
    role:string;
    contact?:string;
    forums: Array<Forum>;
    constructor(id:string, username:string, password:string, email:string, imgUrl:string,name:string, role:string, contact?:string) {
        this.id = id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.name=name;
        this.imgUrl = imgUrl;
        this.role = role;
        this.contact=contact;
        this.forums = [];
    }
}

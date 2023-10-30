import { Forum } from "./forum";

export class User {
    id:string;
    username:string;
    password:string;
    email:string;
    name:string;
    imgUrl:string|null = null;
    role:string|null = null;
    contact:string|null = null;
    forums: Array<Forum>;
    constructor(id:string, username:string, password:string, email:string,name:string) {
        this.id = id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.name=name;
        this.forums = [];
    }
}

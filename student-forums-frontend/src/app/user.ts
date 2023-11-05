import { Forum } from "./forum";
import { Section } from "./section";

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
    section: Section|null = null;
    constructor(id:string, username:string, password:string, email:string,name:string) {
        this.id = id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.name=name;
        this.forums = [];
    }
}

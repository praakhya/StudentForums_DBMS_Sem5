import { User } from "./user";
import { Forum } from "./forum";
export class Faculty extends User {
    jobTitle:string|null;
    department:string;
    domains:Array<string>;
    publications!:Array<string>;
    constructor(
        id:string|null,
        username:string,
        password:string,
        email:string,
        name:string,
        imgUrl:string|null,
        jobTitle:string|null,
        department:string,
        domains:Array<string>,
        publications:Array<string>,
        contact:string|null,
    ) {
        super(id!,username, password, email, name);
        this.jobTitle = jobTitle;
        this.department = department;
        this.domains = domains;
        this.publications = publications;
        this.imgUrl = imgUrl;
        this.contact = contact;
    
    }

    
}

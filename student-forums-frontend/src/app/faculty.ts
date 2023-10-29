import { User } from "./user";
import { Forum } from "./forum";
export class Faculty extends User {
    jobTitle!:string;
    department:string;
    domains!:Array<string>;
    publications!:Array<string>;
    constructor(
        id:string,
        username:string,
        password:string,
        email:string,
        name:string,
        imgUrl:string,
        role:string,
        jobTitle:string,
        department:string,
        domains:Array<string>,
        publications:Array<string>,
        contact?:string,
    ) {
        super(id,username, password, email, imgUrl, name, role, contact);
        this.jobTitle = jobTitle;
        this.department = department;
        this.domains = domains;
        this.publications = publications;
    }

    
}

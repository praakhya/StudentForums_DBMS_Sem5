import { User } from "./user";

export class Student extends User{
    department:string
    classId:string|null
    memberships: Array<string>
    publications: Array<string>
    skills: Array<string>
    rollNo: string = ""
    constructor(
        id:string|null,
        username:string,
        password:string,
        email:string,
        name:string,
        imgUrl:string|null,
        contact:string|null,
        rollNo:string,
        department:string,
        classId:string|null,
        memberships: Array<string>,
        publications: Array<string>,
        skills: Array<string>
    ) {
        super(id!,username, password, email, name);
        this.department = department;
        this.classId = classId;
        this.memberships = memberships;
        this.publications = publications;
        this.skills = skills;
        this.imgUrl = imgUrl;
        this.contact = contact;
        this.rollNo = rollNo;
    }
}

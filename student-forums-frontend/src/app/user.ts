export class User {
    username:String;
    password:String;
    email:String;
    name:String;
    imgUrl:String;
    role:String;
    contact?:String;
    constructor(username:String, password:String, email:String, imgUrl:String,name:String, role:String, contact?:String) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.name=name;
        this.imgUrl = imgUrl;
        this.role = role;
        this.contact=contact;
    }
}

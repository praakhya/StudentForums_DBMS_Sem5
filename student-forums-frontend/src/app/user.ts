export class User {
    username:String;
    password:String;
    email:String;
    name:String;
    role:String;
    imageData?:Blob;
    mimeType?:String;
    contact?:String;
    constructor(username:String, password:String, email:String, name:String, role:String, imageData?:Blob, mimeType?:String, contact?:String) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.name=name;
        this.role = role;
        this.imageData=imageData;
        this.mimeType=mimeType;
        this.contact=contact;
    }
}

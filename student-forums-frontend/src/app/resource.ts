export class Resource {
    id:string;
    name:string;
    ownerName:string;
    validatorId:string;
    validated:boolean;
    dateOfPublish:Date;
    contentType:string;
    url:string;
    constructor(id:string, name:string, ownerName:string, validatorId:string, validated:boolean, dateOfPublish:Date,contentType:string,url:string) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.validated = validated;
        this.validatorId = validatorId;
        this.dateOfPublish = dateOfPublish;
        this.contentType = contentType;
        this.url = url;

    }
}

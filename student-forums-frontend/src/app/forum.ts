import { Faculty } from "./faculty";

export class Forum {
    id:string;
    admin: Faculty|null;
    name: string;
    resources: null;
    users: null;
    constructor(id:string, name: string) {
        this.id = id;
        this.name = name;
        this.admin = null;
        this.resources = null;
        this.users = null;
    }
}

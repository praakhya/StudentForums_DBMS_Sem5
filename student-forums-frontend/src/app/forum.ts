import { Faculty } from "./faculty";
import { User } from "./user";

export class Forum {
    id:string;
    admin: Faculty|null;
    name: string;
    resources: null;
    users: Array<User>;
    constructor(id:string, name: string) {
        this.id = id;
        this.name = name;
        this.admin = null;
        this.resources = null;
        this.users = [];
    }
}

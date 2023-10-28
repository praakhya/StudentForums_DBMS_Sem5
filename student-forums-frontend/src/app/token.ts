export class Token {
    token: string;
    constructor(token:string) {
        if (token) {
            this.token = token;
        }
        else {
            this.token = "";
        }
    }
}

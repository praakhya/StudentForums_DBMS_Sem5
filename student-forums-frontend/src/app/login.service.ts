import { Injectable } from '@angular/core';
import * as moment from "moment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor() {
  }
  public isLoggedIn() {
    return localStorage.getItem("token")!=null;
  }

  public isLoggedOut() {
    return !this.isLoggedIn();
  }
  
}

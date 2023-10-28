import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { Token } from './token';
import * as moment from "moment";
import { UserService } from './user.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public baseUrl = "/api";
  constructor(private httpClient: HttpClient, private userService:UserService) { 
  }
  public setUserToken(username: String, password: String):Observable<any> {
    if (!localStorage.getItem("token")) {
      const httpHeaders = new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8',
      });
      let options = {
        headers: httpHeaders
      };
      const body = {
        "username": username,
        "password": password
      }
      return this.httpClient.post<Token>(this.baseUrl + "/auth", body, options);
    }
    return of(new Token(localStorage.getItem("token")!))
    
  }
  public postUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.baseUrl + "/users", user);
  }
  public setSession(authResult: string) {
    localStorage.setItem('token', authResult);
  }

  logout() {
    localStorage.removeItem("token");
  }

  
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { Token } from './token';
import { of, catchError, Observable, BehaviorSubject } from 'rxjs';
import { Forum } from './forum';
import { Output, EventEmitter } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public baseUrl = "/api";
  @Output()
  user: BehaviorSubject<User|null> = new BehaviorSubject<User|null>(null)
  constructor(private httpClient: HttpClient) {
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
    this.getUser()?.pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
      console.error('Session Time Out', error);
      localStorage.clear();
      return of();
    })).subscribe(u => {
      var user = u;
      console.log(u)
      if (u) {
        user.imgUrl = `/api/user/image/${u.username}`;
      }
      localStorage.setItem("user", JSON.stringify(user));
      this.user.next(u)
    })
    console.log("token set at login")
  }

  logout() {
    localStorage.clear();
  }
  getUser(): Observable<User>|null {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.get<User>(this.baseUrl+"/user",options);
  }
  
  getForum(id:string): Observable<Forum>|null {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.get<Forum>(`${this.baseUrl}/forum/${id}`,options);
  }
  postForum(name:string, id:string) {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    let body = {
      "adminId":id,
      "name":name
    }
    console.log("options in user service: ",options)
    return this.httpClient.post<Forum>(`${this.baseUrl}/forum`,body,options);
  }
  
  public isLoggedIn() {
    return localStorage.getItem("token")!=null;
  }

  public isLoggedOut() {
    return !this.isLoggedIn();
  }

  
}

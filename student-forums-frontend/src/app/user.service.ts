import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from './user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Token } from './token';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  public baseUrl = "/api";
  constructor(private httpClient: HttpClient) {
    var tokenFromStorage = localStorage.getItem("token");
    if (tokenFromStorage) {
      this.getUser();
    }
    
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
  
}

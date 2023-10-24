import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public baseUrl = "/api";

  constructor(private httpClient: HttpClient) { }
  public getUser(username:String): Observable<any> { 
    return this.httpClient.get(this.baseUrl+"/student/"+username); 
  }
}

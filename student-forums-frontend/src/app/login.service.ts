import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loggedIn: BehaviorSubject<boolean>;
  constructor() {
    this.loggedIn = new BehaviorSubject<boolean>(false);
  }
  getValue(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }
  setValue(newValue:boolean): void {
    this.loggedIn.next(newValue);
  }
}

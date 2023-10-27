import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from './user';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loggedInUser: BehaviorSubject<User>|null;

  constructor() {
    this.loggedInUser = null;
  }

  getValue(): Observable<User>|null {
    if (this.loggedInUser)
      return this.loggedInUser.asObservable();
    return null;
  }
  setValue(newValue:User): void {
    if (this.loggedInUser) {
      this.loggedInUser.next(newValue);
      return;
    }
    this.loggedInUser = new BehaviorSubject<User>(newValue);
  }
}

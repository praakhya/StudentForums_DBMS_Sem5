import { Injectable, Output } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class UIService {
  public baseUrl = "/api";
  @Output()
  forumSettingMode: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false)
  constructor(private httpClient: HttpClient) {
  }

  
}

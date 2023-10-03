import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-helloworld',
  templateUrl: './helloworld.component.html',
  styleUrls: ['./helloworld.component.css']
})

export class HelloworldComponent {
  text: any;
constructor(private http: HttpClient) { //injecting HttpClient which is used for Get, Push, Pop, Head etc.
    this.text="";
    this.getData();

  }
  getData() {
    this.http.get('/api/hello' ) //Get request to /api/hello path of spring server
      .subscribe(data => {
        console.log(data)
        this.text = data;
      });
  }
}
// proxy.conf.json contains the connection to the spring server so that both are served from the same port avoiding CORS error.
/*
add "options": {
            "proxyConfig": "src/proxy.conf.json"
          }
in angular.json > serve (after builder)
*/
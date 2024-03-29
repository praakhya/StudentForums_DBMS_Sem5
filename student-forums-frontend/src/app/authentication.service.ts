import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { User } from './user';
import { Token } from './token';
import { of, catchError, Observable, BehaviorSubject } from 'rxjs';
import { Forum } from './forum';
import { Output, EventEmitter } from '@angular/core';
import { Student } from './student';
import { Faculty } from './faculty';
import { Post } from './post';
import { ProfileImage } from './profile-image';
import { Resource } from './resource';
import { Section } from './section';
import { Notification } from './notification';
import { PostCount } from './postCount';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public baseUrl = "/api";
  @Output()
  user: BehaviorSubject<User|null> = new BehaviorSubject<User|null>(null)
  posts: BehaviorSubject<Array<Post>> = new BehaviorSubject<Array<Post>>([])
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
  public postStudent(student: Student): Observable<Student> {
    return this.httpClient.post<Student>(this.baseUrl + "/student", student);
  }
  public getStudent(studentUsername: string): Observable<Student> {
    var path = `${this.baseUrl}/student/${studentUsername}`
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in get student service: ",options)
    return this.httpClient.get<Student>(path, options);
  }
  public getFaculty(facultyUsername:string): Observable<Faculty> {
    var path = `${this.baseUrl}/faculty/${facultyUsername}`
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in get faculty service: ",options)
    return this.httpClient.get<Faculty>(path, options);
  }
  public postFaculty(faculty: Faculty): Observable<Faculty> {
    return this.httpClient.post<Faculty>(this.baseUrl + "/faculty", faculty);
  }
  public setSession(authResult: string) {
    localStorage.setItem('token', authResult);
    this.getUser()?.pipe(catchError((error: any, caught: Observable<any>): Observable<any> => {
      console.error('Session Time Out', error);
      localStorage.clear();
      return of();
    })).subscribe(u => {
      var user = u;
      console.log("in set session:",u)
      localStorage.setItem("user", JSON.stringify(user));
      this.user.next(u)
      console.log("user in auth:",this.user.getValue())
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
  getForums(): Observable<Array<Forum>> {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.get<Array<Forum>>(`${this.baseUrl}/forum/brief`,options);
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
  deletePost(postId:string, forumId:string) {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.delete<Post>(`${this.baseUrl}/forum/${forumId}/post/${postId}`,options);

  }
  subscribeToForum(username:string, forumId:string) {
    var url = `${this.baseUrl}/forum/${forumId}/subscribe/${username}`
    console.log("subscription url: ",url)
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    let body = {}
    console.log("options in subscribe service: ",options,url)
    return this.httpClient.post<Forum>(url,body,options);
  }
  createPost(forumId:string, data:string, type:string, title:string, parentId:string|null, resourceIds:Array<string>) {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    let body = {
      "type":type,
      "title":title,
      "content":data,
      "parentId":parentId,
      "resourceIds": resourceIds
    }
    console.log("options in user service: ",options)
    return this.httpClient.post<Post>(`${this.baseUrl}/forum/${forumId}/post`,body,options);
  }
  reloadPosts(forumId:string) {
    this.getPosts(forumId).subscribe(p => {
      this.posts.next(p);
    })

  }
  getPosts(forumId:string) {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.get<Array<Post>>(`${this.baseUrl}/forum/${forumId}/post`,options);

  }
  uploadImage(file:any, multiPartVar:string) {
    const formData = new FormData();
    formData.append(multiPartVar, file);
    var user = this.user.getValue();
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    console.log("file name:",file, " form data ", formData)
    const httpHeaders = {
//      'Content-Type':'multipart/form-data',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    // Perform the HTTP request
      return this.httpClient.post<ProfileImage>(`${this.baseUrl}/user/image/${user?.username}`, formData,options)
  }
  uploadFile(file:any, multiPartVar:string) {
    var path = `${this.baseUrl}/resource/file`
    const formData = new FormData();
    formData.append(multiPartVar, file.file);
    var user = this.user.getValue();
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    console.log("file name:",file, " form data ", formData)
    const httpHeaders = {
//      'Content-Type':'multipart/form-data',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    // Perform the HTTP request
      return this.httpClient.post<Resource>(path,formData,options)

  }
  getResources() {
    var user = JSON.parse(localStorage.getItem("user")!) as User; 
    var path = `${this.baseUrl}/resource/user/${user?.username}`
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.get<Array<Resource>>(path,options);
  }
  deleteResource(id:string) {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.delete<Resource>(`${this.baseUrl}/resource/${id}`,options);

  }
  getResource(id:string) {
    var user = JSON.parse(localStorage.getItem("user")!) as User; 
    var path = `${this.baseUrl}/resource/${id}`
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.get<Resource>(path,options);
  }
  isFaculty() {
    var user = this.user.getValue()
    return user?.role == "FACULTY"
  }
  createSection(name:string, repUsername:string, teachUsername:string) {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    let body = {
      "name":name,
      "classRepUsername":repUsername,
      "classTeacherUsername":teachUsername,
    }
    console.log("options in create section service: ",options, body)
    return this.httpClient.post<Section>(`${this.baseUrl}/section`,body,options);
  }
  deleteSection(id:string) {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in delete section service: ",options)
    return this.httpClient.delete<Section>(`${this.baseUrl}/section/${id}`,options);
  }
  deleteUser(forumId:string, userId:string) {
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in delete user service: ",options)
    var path = `/api/forum/${forumId}/subscribe/${userId}`
    return this.httpClient.delete<User>(path,options);
  }
  getNotifications(userId:string) {
    var user = JSON.parse(localStorage.getItem("user")!) as User; 
    var path = `${this.baseUrl}/dashboard/notifications/${userId}`
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.get<Array<Notification>>(path,options);
  }
  deleteNotifications(userId:string) {
    var user = JSON.parse(localStorage.getItem("user")!) as User; 
    var path = `${this.baseUrl}/dashboard/notifications/${userId}`
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in user service: ",options)
    return this.httpClient.delete(path,options);
  }
  updateStudentProfile(student:Student) {
    var path = `${this.baseUrl}/student`
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in update student service: ",options)
    return this.httpClient.patch<Student>(path,student,options);

  }
  updateFacultyProfile(faculty:Faculty) {
    var path = `${this.baseUrl}/faculty`
    console.log("authorization: ",localStorage.getItem("token")?.toString())
    const httpHeaders = {
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in update faculty service: ",options)
    return this.httpClient.patch<Faculty>(path,faculty,options);

  }
  public getPostCount(userId: string): Observable<PostCount> {
    var path = `${this.baseUrl}/dashboard/posts/count/${userId}`
    const httpHeaders = {
      'Content-Type':'application/json; charset=utf-8',
      'Authorization': `Bearer ${localStorage.getItem("token")?.toString()}`
    };
    let options = {
      headers: httpHeaders
    };
    console.log("options in get student service: ",options)
    return this.httpClient.get<PostCount>(path, options);
  }

  
}

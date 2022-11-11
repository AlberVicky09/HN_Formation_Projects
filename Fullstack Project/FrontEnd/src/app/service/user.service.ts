import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  //Url of the backend
  private url = "http://localhost:8080/HNProfiles/users";

  //Cached users
  public userLoaded : boolean = false;
  private userStored : User[] = new Array();

  constructor(private http : HttpClient) { }

  //Getter/setter of cached users
  getUserStored(){
    return this.userStored;
  }

  setUserStored(users : User[]){
    this.userLoaded = true;
    this.userStored = users;
  }

  //Backend user method
  getUsers() : Observable<User[]>{
    return this.http.get<User[]>(this.url);
  }

  getUserById(id : number) : Observable<User>{
    return this.http.get<User>(this.url + "/" + id);
  }

  addUser(user : User) : Observable<boolean>{
    return this.http.post<boolean>(this.url, user);
  }

  editUser(id : number, user : User) : Observable<boolean>{
    return this.http.put<boolean>(this.url + "/" + id, user);
  }

  removeUser(id : number) : Observable<boolean>{
    return this.http.delete<boolean>(this.url + "/" + id)
  }
}

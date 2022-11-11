import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TypeUser } from '../model/TypeUser';

@Injectable({
  providedIn: 'root'
})
export class TypeService {
  //Url of the backend
  private url = "http://localhost:8080/HNProfiles/typeUsers";

  //Cached users
  public typeLoaded : boolean = false;
  private typeStored : TypeUser[] = new Array();

  constructor(private http : HttpClient) { }

  //Getter/setter of cached users
  getTypesStored(){
    return this.typeStored;
  }

  setTypesStored(types : TypeUser[]){
    this.typeStored = types;
  }

  //Backend user method
  getTypes() : Observable<TypeUser[]>{
    return this.http.get<TypeUser[]>(this.url);
  }

  getTypeById(id : number) : Observable<TypeUser>{
    return this.http.get<TypeUser>(this.url + "/" + id);
  }

  addType(typeUser : TypeUser) : Observable<boolean>{
    console.log("Adding type");
    return this.http.post<boolean>(this.url, typeUser);
  }

  editType(id : number, typeUser : TypeUser) : Observable<boolean>{
    return this.http.put<boolean>(this.url + "/" + id, typeUser);
  }

  removeType(id : number) : Observable<boolean>{
    return this.http.delete<boolean>(this.url + "/" + id)
  }
}

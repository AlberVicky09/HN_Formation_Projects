import { Injectable } from '@angular/core';
import { IMovie } from '../model/IMovie';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  url = "http://localhost:8080/netflix/";

  private moviesStored : IMovie[] = new Array();
  public moviesLoaded : boolean = false;

  constructor(private http : HttpClient) {}

  setMoviesStored(movies : IMovie[]){
    this.moviesStored = movies;
  }

  getMoviesStored(){
    return this.moviesStored;
  }

  getMovies() : Observable<IMovie[]>{
    return this.http.get<IMovie[]>(this.url + "movieList");
  }

  getMovieByID(id : number) : Observable<IMovie>{
    return this.http.get<IMovie>(this.url + "getMovie/" + id);
  }

  addMovie(movie : IMovie) : Observable<boolean>{
    return this.http.post<boolean>(this.url + "addMovie", movie);
  }

  editMovie(id : number, movie : IMovie) : Observable<boolean>{
    return this.http.post<boolean>(this.url + "editMovie/" + id, movie);
  }

  removeMovie(id : number) : Observable<boolean>{
    return this.http.delete<boolean>(this.url + "removeMovie/" + id);
  }
}

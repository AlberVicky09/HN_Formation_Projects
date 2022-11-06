import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IMovie } from '../model/IMovie';
import { MoviesService } from '../services/movies.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  movies : IMovie[] = new Array();

  constructor(private movieService : MoviesService, private router : Router) { }

  ngOnInit(): void {
    if(this.movieService.moviesLoaded)
      this.movies = this.movieService.getMoviesStored();
    else{
      this.movieService.getMovies()
        .subscribe(movies => {
          this.movies = movies;
          this.movieService.moviesLoaded = true;
          this.movieService.setMoviesStored(movies);
        })
    }
  }

  checkMovie(movie : IMovie){
    this.router.navigate(['/contentDisplay', -1, movie.id]);
  }

  enterEditMovie(index : number){
    this.router.navigate(['/createContent', -1, true, index]);
  }

  removeMovie(index : number){
    this.movieService.removeMovie(index)
      .subscribe(removed => {
        if(removed){
          this.movieService.getMovies()
            .subscribe(movies => {
              this.movies = movies;
              this.movieService.moviesLoaded = true;
              this.movieService.setMoviesStored(movies);
            })
        }
      })
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Movie } from '../model/Movie';
import { Series } from '../model/Series';
import { MoviesService } from '../services/movies.service';
import { SeriesService } from '../services/series.service';

@Component({
  selector: 'app-content-display',
  templateUrl: './content-display.component.html',
  styleUrls: ['./content-display.component.css']
})
export class ContentDisplayComponent implements OnInit {

  public type : number = 0;
  id : number = 0;
  movie : Movie = new Movie("",0,"");
  series : Series = new Series("", 0, new Array(), "", 0);

  constructor(private activatedRoute : ActivatedRoute,
    private moviesService : MoviesService,
    private seriesService : SeriesService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .subscribe(
        (parameters : ParamMap) => {
          this.type = parseInt(parameters.get("type") || '');
          this.id = parseInt(parameters.get("contentID") || '')
          if(this.type == -1){
            this.moviesService.getMovieByID(this.id)
              .subscribe(movie =>{
                this.movie = movie;
              })
          }else{
            this.seriesService.getSeriesByID(this.id)
              .subscribe(series =>{
                this.series = series;
              })
          }
        }
      )
  }

}

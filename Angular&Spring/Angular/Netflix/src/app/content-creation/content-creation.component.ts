import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Movie } from '../model/Movie';
import { Series } from '../model/Series';
import { MoviesService } from '../services/movies.service';
import { SeriesService } from '../services/series.service';

export let numSeasons : number = 1;

@Component({
  selector: 'app-content-creation',
  templateUrl: './content-creation.component.html',
  styleUrls: ['./content-creation.component.css']
})

export class ContentCreationComponent implements OnInit{

  public seasons : number = 1;
  public creationType : number = -1;
  public editMode : boolean = false;
    
  retrieveEnded : boolean = false;
  editID : number = -1;
  movie : Movie = new Movie("",0,"");
  series : Series = new Series("", 0, new Array(), "", 0);

  private parsedChapters : number[] = new Array();

  constructor(private movieService : MoviesService,
    private seriesService : SeriesService,
    private activatedRoute : ActivatedRoute,
    private router : Router) { }
  
  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      (parameters : ParamMap) => {
        this.creationType = parseInt(parameters.get("contentType") || '');
        this.editMode = parameters.get("editMode") == "true";
        if(this.editMode){
          this.editID = parseInt(parameters.get("contentID") || '');
          if(this.creationType == -1){
            this.movieService.getMovieByID(this.editID)
              .subscribe(movie => {
                this.movie = movie;
                this.movieCreationForm.setValue({
                  title : movie.title,
                  duration : movie.duration,
                  synopsis : movie.synopsis
                });
              });
          }else{
            this.seriesService.getSeriesByID(this.editID)
              .subscribe(series => {

                this.series = series;

                this.seasons = series.numSeasons;  

                this.seriesCreationForm.setValue({
                  title : series.title,
                  numSeasons : series.numSeasons,
                  numChapters1 : series.numChapters[0],
                  synopsis : series.synopsis,
                  releaseDate : series.releaseDate
                });

                for(let i = 1; i < series.numSeasons; i++){
                  console.log(series.numChapters[i-1]);
                  this.seriesCreationForm.addControl(`numChapters${i+1}`, new FormControl(series.numChapters[i], Validators.compose([Validators.required, Validators.min(1)])));              
                }
                this.retrieveEnded = true;
              }
            );
          }
        }else
          this.retrieveEnded = true;
      }
    )
  }

  movieCreationForm : FormGroup = new FormGroup({
    title : new FormControl('', Validators.required),
    duration : new FormControl('', Validators.compose([
      Validators.required, Validators.pattern('^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$')
    ])),
    synopsis : new FormControl('', Validators.required)
  });
  
  seriesCreationForm : FormGroup = new FormGroup({
    title : new FormControl('', Validators.required),
    numSeasons : new FormControl(1, Validators.compose([
      Validators.required, Validators.min(1)
    ])),
    numChapters1 : new FormControl('', Validators.compose([
      Validators.required, Validators.min(1)
    ])),
    synopsis : new FormControl('', Validators.required),
    releaseDate : new FormControl('', Validators.compose([
      Validators.required, Validators.pattern(/(^(((0[1-9]|1[0-9]|2[0-8])[\/](0[1-9]|1[012]))|((29|30|31)[\/](0[13578]|1[02]))|((29|30)[\/](0[4,6,9]|11)))[\/](19|[2-9][0-9])\d\d$)|(^29[\/]02[\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/)
    ]))
  });
  
  movieCreation(){
    if(this.movieCreationForm.valid && !this.editMode){
      this.movieService.addMovie(new Movie(
        this.movieCreationForm.get('title')?.value,
        this.movieCreationForm.get('duration')?.value,
        this.movieCreationForm.get('synopsis')?.value
        )
      ).subscribe(worked =>{
        console.log(worked);
      })
      
      this.movieService.moviesLoaded = false;
      this.movieCreationForm.reset();
    }
  }

  movieModification(){
    if(this.movieCreationForm.valid){
      this.movieService.editMovie(this.editID, new Movie(
        this.movieCreationForm.get('title')?.value,
        this.movieCreationForm.get('duration')?.value,
        this.movieCreationForm.get('synopsis')?.value
      )).subscribe(worked => {
        console.log(worked);
        this.movieService.moviesLoaded = false;
        this.router.navigate(['']);
      })
    }
  }

  seriesCreation(){
    if(this.seriesCreationForm.valid && !this.editMode){
      
      for(let i = 1; i <= this.seasons; i++)
        this.parsedChapters.push(this.seriesCreationForm.get(`numChapters${i}`)?.value)

      this.seriesService.addSeries(new Series(
        this.seriesCreationForm.get('title')?.value,
        parseInt(this.seriesCreationForm.get('numSeasons')?.value),
        this.parsedChapters,
        this.seriesCreationForm.get('synopsis')?.value,
        this.seriesCreationForm.get('releaseDate')?.value
        )
      ).subscribe(worked =>{
        console.log(worked);
      })
      
      this.parsedChapters = new Array();
      this.seriesService.seriesLoaded = false;
      this.seriesCreationForm.reset();
    }
  }

  seriesModification(){
    if(this.seriesCreationForm.valid){
      
      for(let i = 1; i <= this.seasons; i++)
        this.parsedChapters.push(this.seriesCreationForm.get(`numChapters${i}`)?.value)

      this.seriesService.editSeries(this.editID, new Series(
        this.seriesCreationForm.get('title')?.value,
        parseInt(this.seriesCreationForm.get('numSeasons')?.value),
        this.parsedChapters,
        this.seriesCreationForm.get('synopsis')?.value,
        this.seriesCreationForm.get('releaseDate')?.value
        )
      ).subscribe(worked =>{
        console.log(worked);
        this.seriesService.seriesLoaded = false;
        this.router.navigate(['']);
      })
    }
  }

  seriesSeasonsEvent$ = this.seriesCreationForm.controls["numSeasons"].valueChanges.subscribe(value =>{
    if(this.retrieveEnded){
      console.log("valueChanged detected");
      if(value > numSeasons){
        do{
          numSeasons++;
          this.seriesCreationForm.addControl(`numChapters${numSeasons}`, new FormControl('', Validators.compose([Validators.required, Validators.min(1)])))
        }while(value > numSeasons);
      }else if(value < numSeasons){
        do{
          numSeasons--;
          this.seriesCreationForm.removeControl(`numChapters${numSeasons}`);
        }while(value < numSeasons)
      }else{
        console.log("value not changed");
      }
      this.seasons = numSeasons;
    }
  })

  numSequence(n: number): Array<number> {
    return Array.from({length: n}, (_, i) => i + 1)
  }
}

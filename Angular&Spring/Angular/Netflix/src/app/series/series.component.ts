import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ISeries } from '../model/ISeries';
import { SeriesService } from '../services/series.service';

@Component({
  selector: 'app-series',
  templateUrl: './series.component.html',
  styleUrls: ['./series.component.css']
})
export class SeriesComponent implements OnInit {

  series : ISeries[] = new Array();

  constructor(private seriesService : SeriesService, private router : Router) { }

  ngOnInit(): void {
    if(this.seriesService.seriesLoaded)
      this.series = this.seriesService.getSeriesStored();
    else{
      this.seriesService.getSeries()
        .subscribe(series => {
          this.series = series;
          this.seriesService.seriesLoaded = true;
          this.seriesService.setSeriesStored(series);
        })
    }
  }

  checkSeries(series : ISeries){
    this.router.navigate(['/contentDisplay', 1, series.id]);
  }

  enterEditSeries(index : number){
    this.router.navigate(['/createContent', 1, true, index]);
  }

  removeSeries(index : number){
    this.seriesService.removeSeries(index)
      .subscribe(removed =>{
        if(removed){
          this.seriesService.getSeries()
            .subscribe(series => {
              this.series = series;
              this.seriesService.seriesLoaded = true;
              this.seriesService.setSeriesStored(series);
            })
        }
      })
  }
}

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { Injectable } from '@angular/core';
import { ISeries } from '../model/ISeries';

@Injectable({
  providedIn: 'root'
})
export class SeriesService {

  url = "http://localhost:8080/netflix/";

  private seriesStored : ISeries[] = new Array();
  public seriesLoaded : boolean = false;

  constructor(private http : HttpClient) { }

  setSeriesStored(series : ISeries[]){
    this.seriesStored = series;
  }

  getSeriesStored(){
    return this.seriesStored;
  }

  getSeries() : Observable<ISeries[]>{
    return this.http.get<ISeries[]>(this.url + "seriesList");
  }

  getSeriesByID(id : number) : Observable<ISeries>{
    return this.http.get<ISeries>(this.url + "getSeries/" + id);
  }

  addSeries(series : ISeries) : Observable<boolean>{
    return this.http.post<boolean>(this.url + "addSeries", series);
  }

  editSeries(id : number, series : ISeries) : Observable<boolean>{
    return this.http.post<boolean>(this.url + "editSeries/" + id, series);
  }

  removeSeries(id : number) : Observable<boolean>{
    return this.http.delete<boolean>(this.url + "removeSeries/" + id);
  }
}

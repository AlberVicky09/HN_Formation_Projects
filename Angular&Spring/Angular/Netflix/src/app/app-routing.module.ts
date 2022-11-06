import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContentCreationComponent } from './content-creation/content-creation.component';
import { ContentDisplayComponent } from './content-display/content-display.component';
import { DefaultComponent } from './default/default.component';
import { MoviesComponent } from './movies/movies.component';
import { SeriesComponent } from './series/series.component';

const routes: Routes = [
  {path:'', component:DefaultComponent},
  {path:'contentDisplay/:type/:contentID', component:ContentDisplayComponent},
  {path:'createContent/:contentType/:editMode/:contentID', component:ContentCreationComponent},
  {path:'displaySeries', component:SeriesComponent},
  {path:'displayMovies', component:MoviesComponent},
  {path:'**', redirectTo:''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

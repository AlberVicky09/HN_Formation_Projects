import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http'
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MoviesComponent } from './movies/movies.component';
import { SeriesComponent } from './series/series.component';
import { ContentCreationComponent } from './content-creation/content-creation.component';
import { DefaultComponent } from './default/default.component';
import { ContentDisplayComponent } from './content-display/content-display.component';

@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    SeriesComponent,
    ContentCreationComponent,
    DefaultComponent,
    ContentDisplayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

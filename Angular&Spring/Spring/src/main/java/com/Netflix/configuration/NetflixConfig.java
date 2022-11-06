package com.Netflix.configuration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Netflix.util.Movie;
import com.Netflix.util.Series;

@Configuration
public class NetflixConfig {

	@Bean
	@Qualifier("series")
	public List<Series> getSeries(){
		List<Series> series = new ArrayList<>();
		series.add(new Series(0L, "Stranger Things", 4, new int[]{12,10,13,10}, "Bla bla bla", "15/07/2016"));
		series.add(new Series(1L, "Sex Education", 3, new int[]{10,5,8}, "Bla bla bla", "11/01/2019"));
		series.add(new Series(2L, "Yo nunca", 2, new int[] {20,24}, "Bla bla bla", "23/07/2021"));
		series.add(new Series(3L, "Peaky Blinders", 4, new int[] {14,12,16,14}, "Bla bla bla", "10/03/2016"));
		series.add(new Series(4L, "Breaking Bad", 3, new int[]{10,11,10}, "Bla bla bla", "11/08/2020"));
		series.add(new Series(5L, "Love, Death + Robots", 1, new int[]{24}, "Bla bla bla", "01/01/2023"));
		
		return series;
	
	}
	
	@Bean
	@Qualifier("movies")
	public List<Movie> getMovies(){
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie(0L, "Regreso al futuro", "01:45", "Bla bla"));
		movies.add(new Movie(1L, "Viernes 13", "2:04", "Bla bla"));
		movies.add(new Movie(2L, "¡Canta!", "1:33", "Bla bla"));
		movies.add(new Movie(3L, "Wonder", "0:57", "Bla bla"));
		
		return movies;
	}
}

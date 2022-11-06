package com.Netflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Netflix.util.Movie;
import com.Netflix.util.Series;

//Mark class as rest controller and set url
@RestController
@CrossOrigin
@RequestMapping("/netflix")
public class NetflixController {
	
	//Lists of data
	@Autowired
	@Qualifier("series")
	List<Series> series;
	
	@Autowired
	@Qualifier("movies")
	List<Movie> movies;
	
	//Series methods
	@GetMapping("/seriesList")
	public List<Series> getSeries(){
		return series;
	}
	
	@GetMapping("/getSeries/{seriesID}")
	public Series getSeriesByID(@PathVariable("seriesID") int id) {
		return series.get(id);
	}
		
	@PostMapping("/addSeries")
	public boolean addSeries(@RequestBody Series s) {
		for(int i = 0; i < series.size(); i++) {
			if(series.get(i).title == s.title) {
				series.set(i, s);
				return false;
			}
		}
		s.setId(Long.valueOf(series.size()));
		return series.add(s);
	}
	
	@PostMapping("/editSeries/{seriesID}")
	public boolean editSeries(@PathVariable("seriesID") int id, @RequestBody Series s) {
		series.set(id, s);
		return true;
	}
	
	@DeleteMapping("/removeSeries/{seriesID}")
	public boolean removeSeries(@PathVariable("seriesID") int id) {
		for(Series s : series) {
			if(s.id == id) {
				series.remove(s);
				return reorderSeriesIds();
			}
		}
		
		return false;
	}
	
	private boolean reorderSeriesIds() {
		for(int i = 0; i < series.size(); i++)
			series.get(i).setId(Long.valueOf(i));
		return true;
	}
	
	//Movies methods
	@GetMapping("/movieList")
	public List<Movie> getMovies(){
		return movies;
	}
	
	@GetMapping("/getMovie/{movieID}")
	public Movie getMovieByID(@PathVariable("movieID") int id) {
		return movies.get(id);
	}
	
	@PostMapping("/addMovie")
	public boolean addMovie(@RequestBody Movie m) {
		for(int i = 0; i < movies.size(); i++) {
			if(movies.get(i).title == m.title) {
				movies.set(i, m);
				return false;
			}
		}
		m.setId(Long.valueOf(movies.size()));
		return movies.add(m);
	}
	
	@PostMapping("editMovie/{movieID}")
	public boolean editMovie(@PathVariable("movieID") int id, @RequestBody Movie m) {
		movies.set(id, m);
		return true;
	}
	
	@DeleteMapping("/removeMovie/{movieID}")
	public boolean removeMovie(@PathVariable("movieID") int id) {
		for(Movie movie : movies) {
			if(movie.id == id) {
				movies.remove(movie);
				return reorderMovieIds();
			}
		}
		
		return false;
	}
	
	private boolean reorderMovieIds() {
		for(int i = 0; i < movies.size(); i++)
			movies.get(i).setId(Long.valueOf(i));
		return true;
	}
}

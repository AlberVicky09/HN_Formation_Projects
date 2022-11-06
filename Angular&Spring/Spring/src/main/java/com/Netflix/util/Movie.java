package com.Netflix.util;

public class Movie {
	public Long id;
	public String title;
	public String duration;
	public String synopsis;
	
	public Movie(Long id, String title, String duration, String synopsis) {
		super();
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.synopsis = synopsis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
}

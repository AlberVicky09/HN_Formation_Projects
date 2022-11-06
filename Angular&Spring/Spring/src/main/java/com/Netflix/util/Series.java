package com.Netflix.util;

import java.util.Date;

public class Series {

	public Long id;
	public String title;
	public int numSeasons;
	public int[] numChapters;
	public String synopsis;
	public String releaseDate;
	
	public Series(Long id, String title, int numSeasons, int[] numChapters, String synopsis, String releaseDate) {
		this.id = id;
		this.title = title;
		this.numSeasons = numSeasons;
		this.numChapters = numChapters;
		this.synopsis = synopsis;
		this.releaseDate = releaseDate;
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

	public int getNumSeasons() {
		return numSeasons;
	}

	public void setNumSeasons(int numSeasons) {
		this.numSeasons = numSeasons;
	}

	public int[] getNumChapters() {
		return numChapters;
	}

	public void setNumChapters(int[] numChapters) {
		this.numChapters = numChapters;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
}

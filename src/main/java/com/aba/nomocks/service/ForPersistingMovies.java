package com.aba.nomocks.service;

import com.aba.nomocks.biz.Movie;

public interface ForPersistingMovies {
	public String saveMovie(Movie movie);
	
	public Movie retrieveMovie(String title, int year);
}

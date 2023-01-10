package com.aba.nomocks.service;

import com.aba.nomocks.biz.Movie;

public class MovieService {
	ForPersistingMovies movieDao;
	
	public MovieService(ForPersistingMovies movieDao) {
		this.movieDao = movieDao;
	}
	
	public String saveMovie(Movie movie) {
		return movieDao.saveMovie(movie);
	}

	public Movie retrieveMovie(String title, int year) {
		return movieDao.retrieveMovie(title, year);
	}
}

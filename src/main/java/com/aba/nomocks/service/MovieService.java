package com.aba.nomocks.service;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDao;

public class MovieService {
	MovieDao movieDao;
	
	public MovieService(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	public void saveMovie(Movie movie) {
		movieDao.saveMovie(movie);
	}

	public Movie retrieveMovie(String title, int year) {
		return movieDao.retrieveMovie(title, year);
	}
}

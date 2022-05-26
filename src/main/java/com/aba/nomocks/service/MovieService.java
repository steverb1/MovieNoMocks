package com.aba.nomocks.service;

import com.aba.nomocks.dao.MovieDao;

public class MovieService {
	MovieDao movieDao;
	
	public MovieService(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	public void saveMovie(String title, int year) {
		movieDao.insertMovie(title, year);
	}
}

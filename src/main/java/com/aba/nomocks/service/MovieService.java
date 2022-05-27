package com.aba.nomocks.service;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDaoMongo;

public class MovieService {
	MovieDaoMongo movieDao;
	
	public MovieService(MovieDaoMongo movieDao) {
		this.movieDao = movieDao;
	}
	
	public void saveMovie(Movie movie) {
		movieDao.saveMovie(movie);
	}
}

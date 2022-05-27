package com.aba.nomocks.dao;

import org.junit.jupiter.api.Test;

import com.aba.nomocks.biz.Movie;

public class MovieDaoTest {
	@Test
	public void testInsertMovie() {
		MovieDaoMongo movieDao = MovieDaoMongo.create();  // Real dao, just to see if mongodb is working.
		movieDao.saveMovie(new Movie("The Princess Bride", 1987));
	}
}

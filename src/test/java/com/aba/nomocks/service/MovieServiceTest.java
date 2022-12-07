package com.aba.nomocks.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDaoMongo;

public class MovieServiceTest {
	@Test
	public void testSaveMovie() {
		MovieDaoMongo movieDao = MovieDaoMongo.createNull();
		MovieService service = new MovieService(movieDao);
		service.saveMovie(new Movie("Bladerunner", 1982));
		
		assertEquals("Bladerunner", movieDao.getLastWrite().title);
		assertEquals(1982, movieDao.getLastWrite().year);
	}
}

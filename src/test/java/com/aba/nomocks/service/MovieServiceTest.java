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
		service.saveMovie(new Movie("The Kid", 2000));
		
		assertEquals("The Kid", movieDao.getLastWrite().title);
		assertEquals(2000, movieDao.getLastWrite().year);
	}
	
	@Test
	public void testRetrieveMovie() {
		MovieDaoMongo movieDao = MovieDaoMongo.createNull();
		MovieService service = new MovieService(movieDao);
		service.saveMovie(new Movie("Return to Me", 2000));
		
		Movie retrievedMovie = service.retrieveMovie("Return to Me", 2000);
		
		assertEquals("Return to Me", retrievedMovie.title);
		assertEquals(2000, retrievedMovie.year);
	}
}

package com.aba.nomocks.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDao;

public class MovieServiceTest {
	@Test
	public void testSaveMovie() {
		MovieDao movieDao = MovieDao.createNull();
		MovieService service = new MovieService(movieDao);
		String movieId = service.saveMovie(new Movie("The Kid", 2000));
		
		assertTrue(movieId != "");
		assertEquals("The Kid", movieDao.getLastWrite().title);
		assertEquals(2000, movieDao.getLastWrite().year);
	}
	
	@Test
	public void testRetrieveMovie() {
		MovieDao movieDao = MovieDao.createNull();
		MovieService service = new MovieService(movieDao);
		String movieId = service.saveMovie(new Movie("Return to Me", 2000));
		assertTrue(movieId != "");
		
		Movie retrievedMovie = service.retrieveMovie("Return to Me", 2000);
		
		assertEquals("Return to Me", retrievedMovie.title);
		assertEquals(2000, retrievedMovie.year);
	}
}

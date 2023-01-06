package com.aba.nomocks.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.aba.nomocks.biz.Movie;

public class MovieDaoIntegrationTest {
	@Test
	public void testSaveAndRetrieveMovie() {
		MovieDao movieDao = MovieDao.create();  // Real dao, just to see if mongodb is working.
		String movieId = movieDao.saveMovie(new Movie("The Princess Bride", 1987));
		assertTrue(movieId != "");
		
		Movie retrievedMovie = movieDao.retrieveMovie("The Princess Bride", 1987);
		assertEquals("The Princess Bride", retrievedMovie.title);
	}
}

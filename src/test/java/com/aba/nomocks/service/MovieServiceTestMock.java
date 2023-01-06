package com.aba.nomocks.service;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDao;

public class MovieServiceTestMock {
	@Test
	public void canSaveAndRetrieveMovieWithMock() {
		MovieDao movieDao = Mockito.mock(MovieDao.class);
		MovieService service = new MovieService(movieDao);
		
		String movieTitle = "The Princess Bride";
		int movieYear = 1987;
		
		Movie movie = new Movie(movieTitle, movieYear);
		String movieId = service.saveMovie(movie );
		
		assertTrue(movieId != "");
		verify(movieDao).saveMovie(movie);
		
		Movie testMovie = new Movie(movieTitle, movieYear);
		when(service.retrieveMovie(movieTitle, movieYear)).thenReturn(testMovie );
		Movie retrievedMovie = service.retrieveMovie(movieTitle, movieYear);
		
		assertEquals(movieTitle, retrievedMovie.title);
		assertEquals(movieYear, retrievedMovie.year);
		verify(movieDao).retrieveMovie(movieTitle, movieYear);
	}
}

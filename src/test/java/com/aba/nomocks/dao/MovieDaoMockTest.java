package com.aba.nomocks.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDao.ForWrappingMongo;

public class MovieDaoMockTest {
	@Test
	public void testSaveAndRetrieveMovieWithMock() {
		ForWrappingMongo mongoMock = mock(ForWrappingMongo.class);
		MovieDao dao = new MovieDao(mongoMock);
		
		String movieTitle = "Fred Clause";
		int movieYear = 2007;
		Movie movie = new Movie(movieTitle, movieYear);
		when(mongoMock.insertOne(movie)).thenReturn("123");
		String movieId = dao.saveMovie(movie );
		assertNotNull(movieId);
		
		verify(mongoMock, times(1)).insertOne(movie);
		
		when(mongoMock.find(movieTitle, movieYear)).thenReturn(movie);
		Movie retrievedMovie = dao.retrieveMovie(movieTitle, movieYear);
		assertEquals(movieTitle, retrievedMovie.title);
		assertEquals(movieYear, retrievedMovie.year);
		verify(mongoMock, times(1)).find(movieTitle, movieYear);
	}
}

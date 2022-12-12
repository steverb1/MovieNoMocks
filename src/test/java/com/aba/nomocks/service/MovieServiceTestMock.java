package com.aba.nomocks.service;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDaoMongo;

public class MovieServiceTestMock {
	@Test
	public void canTestServiceWithMock() {
		MovieDaoMongo movieDao = Mockito.mock(MovieDaoMongo.class);
		MovieService service = new MovieService(movieDao);
		
		Movie movie = new Movie("The Princess Bride", 1987);
		service.saveMovie(movie );
		
		verify(movieDao).saveMovie(movie);
	}
}

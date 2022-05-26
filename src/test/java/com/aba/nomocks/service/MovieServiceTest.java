package com.aba.nomocks.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.aba.nomocks.dao.MovieDao;

public class MovieServiceTest {
	@Test
	public void testSaveMovie() {
		MovieDao movieDao = MovieDao.createNull();
		MovieService service = new MovieService(movieDao);
		service.saveMovie("Groundhog Day", 1993);
		
		assertEquals("Groundhog Day", movieDao.getLastSavedTitle());
		assertEquals(1993, movieDao.getLastSavedYear());
	}
}

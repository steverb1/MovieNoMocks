package com.aba.nomocks.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.aba.nomocks.biz.Movie;

public class MovieDaoTest {
	@Test
	public void testSaveMovie() {
		MovieDaoMongo movieDao = MovieDaoMongo.createNull();
		
		Movie movie = new Movie("test", 1984);
		String id = movieDao.saveMovie(movie);
		
		assertEquals("test", MovieDaoMongo.getLastWrite().title);
	}
}

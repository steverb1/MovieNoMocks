package com.aba.nomocks.dao;

import org.junit.jupiter.api.Test;

public class MovieDaoTest {
	@Test
	public void testInsertMovie() {
		MovieDao movieDao = MovieDao.create();  // Real dao, just to see if mongodb is working.
		movieDao.insertMovie("The Princess Bride", 1987);
	}
}

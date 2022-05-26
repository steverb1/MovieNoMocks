package com.aba.nomocks.dao;

import org.junit.jupiter.api.Test;

public class MovieDaoTest {
	@Test
	public void testInsertMovie() {
		MovieDao movieDao = MovieDao.createNull();
		movieDao.insertMovie("The Princess Bride", 1987);
	}
}

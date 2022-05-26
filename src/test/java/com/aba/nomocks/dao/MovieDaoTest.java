package com.aba.nomocks.dao;

import org.junit.jupiter.api.Test;

public class MovieDaoTest {
	@Test
	public void test1() {
		MovieDao movieDao = new MovieDao();
		movieDao.InsertMovie("The Princess Bride", 1987);
	}
}

package com.aba.nomocks.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDao.ForWrappingMongo;

public class MovieDaoMockTest {
	@Test
	public void testSaveAndRetrieveMovieWithMock() {
		ForWrappingMongo mongoMock = mock(ForWrappingMongo.class);
		MovieDao dao = new MovieDao(mongoMock);
		
		Movie movie = new Movie("Fred Clause", 2007);
		dao.saveMovie(movie );
		
		Document aMovie = new Document()
				.append("title", "Fred Clause")
				.append("year", 2007);
		verify(mongoMock, times(1)).insertOne(argThat(new DocumentMatcher(aMovie)));
		
		when(mongoMock.find("Fred Clause", 2007)).thenReturn(aMovie);
		Movie retrievedMovie = dao.retrieveMovie("Fred Clause", 2007);
		assertEquals("Fred Clause", retrievedMovie.title);
		assertEquals(2007, retrievedMovie.year);
		verify(mongoMock, times(1)).find("Fred Clause", 2007);
	}
}

class DocumentMatcher implements ArgumentMatcher<Document> {

	Document movieToMatch;
	
	DocumentMatcher(Document movieToMatch) {
		this.movieToMatch = movieToMatch;
	}
	
	public boolean matches(Document movie) {
		return movieToMatch.getString("title").equals(movie.getString("title")) &&
				movieToMatch.getInteger("year").equals(movie.getInteger("year"));
	}
}

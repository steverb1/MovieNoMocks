package com.aba.nomocks.dao;

import static org.mockito.Mockito.*;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDao.ForWrappingMongo;

public class MovieDaoMockTest {
	@Test
	public void testSaveMovieWithMock() {
		ForWrappingMongo mongoMock = mock(ForWrappingMongo.class);
		MovieDao dao = new MovieDao(mongoMock);
		
		Movie movie = new Movie("Fred Clause", 2007);
		dao.saveMovie(movie );
		
		Document aMovie = new Document()
				.append("title", "Fred Clause")
				.append("year", 2007);
		verify(mongoMock, times(1)).insertOne(argThat(new DocumentMatcher(aMovie)));
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

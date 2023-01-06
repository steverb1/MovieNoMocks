package com.aba.nomocks.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDao.ForWrappingMongo;

public class MovieDaoMockTest {
//	@Test
//	public void testSaveAndRetrieveMovieWithMock() {
//		ForWrappingMongo mongoMock = mock(ForWrappingMongo.class);
//		MovieDao dao = new MovieDao(mongoMock);
//		
//		String movieTitle = "Fred Clause";
//		int movieYear = 2007;
//		Movie movie = new Movie(movieTitle, movieYear);
//		dao.saveMovie(movie );
//		
//		Document movieDocument = new Document()
//				.append("title", movieTitle)
//				.append("year", movieYear);
//		verify(mongoMock, times(1)).insertOne(argThat(new DocumentMatcher(movieDocument)));
//		
//		when(mongoMock.find(movieTitle, movieYear)).thenReturn(movieDocument);
//		Movie retrievedMovie = dao.retrieveMovie(movieTitle, movieYear);
//		assertEquals(movieTitle, retrievedMovie.title);
//		assertEquals(movieYear, retrievedMovie.year);
//		verify(mongoMock, times(1)).find(movieTitle, movieYear);
//	}
}

//class DocumentMatcher implements ArgumentMatcher<Document> {
//
//	Document movieToMatch;
//	
//	DocumentMatcher(Document movieToMatch) {
//		this.movieToMatch = movieToMatch;
//	}
//	
//	public boolean matches(Document movie) {
//		return movieToMatch.getString("title").equals(movie.getString("title")) &&
//				movieToMatch.getInteger("year").equals(movie.getInteger("year"));
//	}
//}

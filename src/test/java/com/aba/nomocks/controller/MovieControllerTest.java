package com.aba.nomocks.controller;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDaoMongo;
import com.aba.nomocks.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieControllerTest {
    @Test
    public void testSaveMovie() {
        MovieDaoMongo movieDao = MovieDaoMongo.createNull();
        MovieService service = new MovieService(movieDao);

        MovieController controller = new MovieController(service);

        ResponseEntity<String> response = controller.saveMovie(new Movie("The Kid", 2000));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testRetrieveMovie_MovieExists() {
        MovieDaoMongo movieDao = MovieDaoMongo.createNull();
        MovieService service = new MovieService(movieDao);

        MovieController controller = new MovieController(service);

        controller.saveMovie(new Movie("The Kid", 2000));
        ResponseEntity<Movie> response = controller.retrieveMovie("The Kid", 2000);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRetrieveMovie_MovieDoesNotExist() {
        MovieDaoMongo movieDao = MovieDaoMongo.createNull();
        MovieService service = new MovieService(movieDao);

        MovieController controller = new MovieController(service);

        ResponseEntity<Movie> response = controller.retrieveMovie("The Kid", 2000);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRetrieveMovie_MovieYearIncorrect() {
        MovieDaoMongo movieDao = MovieDaoMongo.createNull();
        MovieService service = new MovieService(movieDao);

        MovieController controller = new MovieController(service);

        controller.saveMovie(new Movie("The Kid", 2000));
        ResponseEntity<Movie> response = controller.retrieveMovie("The Kid", 2001);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRetrieveMovie_MovieNameIncorrect() {
        MovieDaoMongo movieDao = MovieDaoMongo.createNull();
        MovieService service = new MovieService(movieDao);

        MovieController controller = new MovieController(service);

        controller.saveMovie(new Movie("The Kid", 2000));
        ResponseEntity<Movie> response = controller.retrieveMovie("The Kid 2", 2000);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

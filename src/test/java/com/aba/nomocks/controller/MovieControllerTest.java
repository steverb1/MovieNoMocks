package com.aba.nomocks.controller;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDaoMongo;
import com.aba.nomocks.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

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
}

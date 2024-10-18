package com.aba.nomocks.controller;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.dao.MovieDaoMongo;
import com.aba.nomocks.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController
{
    MovieService service;

    MovieController(MovieService movieService) {
        this.service = movieService;
    }

    MovieController() {
        MovieDaoMongo movieDao = MovieDaoMongo.create();
        service = new MovieService(movieDao);
    }

    @PostMapping()
    public ResponseEntity<String> saveMovie(@RequestBody Movie movie) {
        String movieId = service.saveMovie(movie);

        return new ResponseEntity<>(movieId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Movie> retrieveMovie(@RequestParam(name = "title") String title, @RequestParam(name = "year") int year) {
        Movie movie = service.retrieveMovie(title, year);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

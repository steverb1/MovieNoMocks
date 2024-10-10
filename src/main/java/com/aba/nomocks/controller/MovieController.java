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
    @PostMapping()
    public ResponseEntity<String> saveMovie(@RequestBody Movie movie) {
        MovieDaoMongo movieDao = MovieDaoMongo.create();
        MovieService service = new MovieService(movieDao);

        String movieId = service.saveMovie(movie);

        return new ResponseEntity<>(movieId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Movie> retrieveMovie(@RequestParam(name = "title") String title, @RequestParam(name = "year") int year) {
        MovieDaoMongo movieDao = MovieDaoMongo.create();
        MovieService service = new MovieService(movieDao);
        Movie movie = service.retrieveMovie(title, year);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

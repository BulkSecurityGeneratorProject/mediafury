package com.prevostc.mediafury.controller;

import com.prevostc.mediafury.model.Movie;
import com.prevostc.mediafury.repository.MovieRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

    private MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces = "application/json")
    public Optional<Movie> search(@RequestParam(value="title") Optional<String> title) {
        Optional<Movie> res;
        if (title.isPresent()) {
            res = movieRepository.findByTitleIgnoreCase(title.get());
        } else {
            res = Optional.empty();
        }
        return res;
    }

}
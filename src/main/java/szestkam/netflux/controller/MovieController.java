package szestkam.netflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import szestkam.netflux.domain.Movie;
import szestkam.netflux.domain.MovieEvent;
import szestkam.netflux.service.MovieService;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:8080")
public class MovieController {

    private final MovieService movieService;

    public MovieController(@Autowired MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id) {
        return movieService.events(id);
    }

    @GetMapping(value = "/{id}")
    Mono<Movie> getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping(value = {"/all", "/", ""})
    Flux<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

}

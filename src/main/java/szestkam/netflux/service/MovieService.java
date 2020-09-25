package szestkam.netflux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import szestkam.netflux.domain.Movie;
import szestkam.netflux.domain.MovieEvent;

public interface MovieService {
    Flux<MovieEvent> events(String movieID);

    Mono<Movie> getMovieById(String id);

    Flux<Movie> getAllMovies();
}

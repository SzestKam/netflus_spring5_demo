package szestkam.netflux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import szestkam.netflux.domain.Movie;
import szestkam.netflux.domain.MovieEvent;
import szestkam.netflux.repository.MovieRepository;
import szestkam.netflux.service.MovieService;

import java.time.Duration;
import java.util.Date;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Flux<MovieEvent> events(String movieId) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink -> movieEventSynchronousSink.next(
                new MovieEvent(movieId, new Date())
        )).delayElements(Duration.of(1, SECONDS));
    }

    @Override
    public Mono<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}

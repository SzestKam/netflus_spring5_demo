package szestkam.netflux.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import szestkam.netflux.domain.Movie;
import szestkam.netflux.repository.MovieRepository;

import java.util.UUID;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public BootstrapCLR(@Autowired MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) {

        movieRepository.deleteAll()
                .thenMany(
                        Flux.just("Matrix", "Ronin", "Minions", "Taxi", "Mortal Machines")
                                .map(title -> new Movie(UUID.randomUUID().toString(), title))
                                .flatMap(movieRepository::save)
                )
                .subscribe(null, null, () -> movieRepository.findAll().subscribe(System.out::println));

    }
}

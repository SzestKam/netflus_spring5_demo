package szestkam.netflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import szestkam.netflux.domain.Movie;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}

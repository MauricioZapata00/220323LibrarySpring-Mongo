package repositories;

import models.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findByResources(String resource);
    Flux<Book> findByThematic(String thematic);
    Mono<Book> findBookByName(String name);
}

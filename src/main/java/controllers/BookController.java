package controllers;

import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import services.BookService;

@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/bookName/{name}")
    public Mono<Book> searchByBookName(@PathVariable("name") String name){
        return this.bookService.getBookByName(name);
    }

    @GetMapping(path = "/booksByResource/{resource}")
    public Flux<Book> searchBooksByResource(@PathVariable("resource") String resource){
        return this.bookService.getBooksByResource(resource);
    }

    @GetMapping(path = "/booksByThematic/{thematic}")
    public Flux<Book> searchBooksByThematic(@PathVariable("thematic") String thematic){
        return this.bookService.getBooksByThematic(thematic);
    }
}

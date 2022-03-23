package services;

import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    //GET METHODS
    public Mono<Book> getBookByName(String name){
        return this.bookRepository.findBookByName(name);
    }

    public Flux<Book> getBooksByResource(String resource){
        return this.bookRepository.findByResources(resource);
    }

    public Flux<Book> getBooksByThematic(String thematic){
        return this.bookRepository.findByThematic(thematic);
    }

    //POST METHODS

    public Mono<Book> createBook(String name, String author, Integer numberOfPages
            , String resources, String thematic){
        Book bookToSave = new Book(name, author, numberOfPages, resources, thematic);
        return this.bookRepository.save(bookToSave);
    }

    //UPDATE METHODS

    public Mono<Book> changeResource(String id, String newResource){
        Mono<Book> bookToChange = this.bookRepository.findById(id);
        return bookToChange.flatMap(book -> {
            book.setId(id);
            book.setResources(newResource);
            return this.bookRepository.save(book);
        });
    }

    public Mono<Book> changeThematic(String id, String newThematic){
        Mono<Book> bookToChange = this.bookRepository.findById(id);
        return bookToChange.flatMap(book -> {
            book.setId(id);
            book.setThematic(newThematic);
            return this.bookRepository.save(book);
        });
    }

    public String borrowBook(String name){
        Mono<Book> book = this.getBookByName(name);
        Book currBook = book.block();
        if (currBook.getAvailable()){
            currBook.setId(currBook.getId());
            currBook.setAvailable(false);
            this.bookRepository.save(currBook);
            return "Here you have the book: " + name;
        }
        return "Sorry, the book has been borrowed";
    }

    public String returnBook(String name){
        Mono<Book> book = this.getBookByName(name);
        Book currBook = book.block();
        if (!currBook.getAvailable()){
            currBook.setId(currBook.getId());
            currBook.setAvailable(true);
            this.bookRepository.save(currBook);
            return "You have returned the book: " + name;
        }
        return "Sorry, this book was not borrow";
    }

    //DELETE METHODS

    public void eraseBook(String id){
        Mono<Book> bookToErase = this.bookRepository.findById(id);
        this.bookRepository.delete(bookToErase.block());
    }
}

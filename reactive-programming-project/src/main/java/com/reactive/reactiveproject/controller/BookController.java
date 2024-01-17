package com.reactive.reactiveproject.controller;

import com.reactive.reactiveproject.entity.Book;
import com.reactive.reactiveproject.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping
    public Mono<Book> create(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping
    public Flux<Book> getAll() {
        return bookService.getAllBook();
        //return bookService.getAllBook().delayElements(Duration.ofSeconds(2)).log();
    }

    @GetMapping("/{bookId}")
    public Mono<Book> getById(@PathVariable int bookId) {
        return bookService.getBookById(bookId);
    }


    @PutMapping("/{bookId}")
    public Mono<Book> updateBook(@RequestBody Book book, @PathVariable int bookId) {
        return bookService.update(book, bookId);
    }

    @DeleteMapping("/{bookId}")
    public Mono<Void> deleteBook(@PathVariable int bookId) {
        return bookService.delete(bookId).log();
    }

    @GetMapping("/search/{bookName}")
    public Flux<Book> searchBookByName(@PathVariable("bookName") String name) {
        return bookService.search(name);
    }

    @GetMapping("/search")
    public Flux<Book> searchNameQuery(@RequestParam("query") String query) {
        return bookService.searchName(query);
    }


}

package com.reactive.reactiveproject.service;

import com.reactive.reactiveproject.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    public Mono<Book> createBook(Book book);

    public Flux<Book> getAllBook();

    public Mono<Book> getBookById(int bookId);

    public Mono<Book> update(Book book, int bookId);

    public Mono<Void> delete(int bookId);

    public Flux<Book> search(String query);


}
